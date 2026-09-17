package com.nttdata.idmccnobe.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.idmccnobe.dto.ResponseStoreLocatorDTO;
import com.nttdata.idmccnobe.dto.UserDto;
import com.nttdata.idmccnobe.dto.UserSearchResDto;
import com.nttdata.idmccnobe.util.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service("StoreLocatorClientService")
@Transactional
public class StoreLocatorClientService extends AbstractService {

    @Autowired
    private ParameterBeService parameterBeService;

    @Autowired
    private UtilsService utilsService;

    private static final List<Integer> DEFAULT_COOP_IDS = Arrays.asList(1, 2, 3);

    @Cacheable(cacheNames = "listPdvCodeDetailsAllCoop", cacheManager = "springCM")
    public List<ResponseStoreLocatorDTO.Pdv> listPdvCodeDetailsAllCoop() {
        RestTemplate restTemplate = utilsService.proxyRestTemplate();
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String apimEndpointUrl = parameterBeService.getParameterValueByName(Constants.APIM_ENDPOINT);
        String path_storelocator_pdvCodeDetails = parameterBeService.getParameterValueByName(Constants.PATH_STORELOCATOR_PDVCODEDETAILS);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        // Usiamo una mappa per deduplicare e preservare l'ordine di inserimento
        Map<String, ResponseStoreLocatorDTO.Pdv> merged = new LinkedHashMap<>();

        for (Integer coopId : DEFAULT_COOP_IDS) {
            String url = apimEndpointUrl + path_storelocator_pdvCodeDetails + "?coopId=" + coopId;
            try {
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
                String body = response.getBody();

                if (response.getStatusCode().is2xxSuccessful() && body != null) {
                    ResponseStoreLocatorDTO dto = mapper.readValue(body, ResponseStoreLocatorDTO.class);
                    if (dto != null && dto.getHeader() != null && "OK".equalsIgnoreCase(dto.getHeader().getResult())
                            && dto.getPayload() != null && dto.getPayload().getPdv() != null) {

                        for (ResponseStoreLocatorDTO.Pdv p : dto.getPayload().getPdv()) {
                            // chiave di deduplica: prima codicePdvCoop, altrimenti idStoreLocator
                            String key = (p.getCodicePdvCoop() != null && !p.getCodicePdvCoop().isEmpty())
                                    ? ("CPC:" + p.getCodicePdvCoop())
                                    : ("ISL:" + (p.getIdStoreLocator() != null ? p.getIdStoreLocator() : "NULL"));

                            // Se vuoi tenere il primo che arriva, lascia così.
                            // Se vuoi sovrascrivere col più recente, fai merged.put(key, p) sempre.
                            merged.putIfAbsent(key, p);
                        }
                    } else {
                        logger.warn("listPdvCodeDetails: header non OK per coopId=" + coopId);
                    }
                } else {
                    logger.error("listPdvCodeDetails: HTTP " + response.getStatusCode() + " per coopId=" + coopId);
                }
            } catch (Exception e) {
                logger.error("listPdvCodeDetails() coopId=" + coopId + " - " + e.getMessage(), e);
            }
        }

        return new ArrayList<>(merged.values());
    }

    public void enrichUserCodicePdv(UserSearchResDto userSearchResDto) {
        if (userSearchResDto == null || userSearchResDto.getPayload() == null) {
            return;
        }
        List<UserDto> users = userSearchResDto.getPayload().getUsers();
        if (users == null || users.isEmpty()) {
            return;
        }

        // Prendi solo gli utenti che hanno un pdvId valorizzato
        Set<String> neededIds = users.stream()
                .map(UserDto::getPdvId)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());

        if (neededIds.isEmpty()) {
            return;
        }

        List<ResponseStoreLocatorDTO.Pdv> pdvs = listPdvCodeDetailsAllCoop();
        if (pdvs == null || pdvs.isEmpty()) {
            return;
        }

        // Se idStoreLocator è numerico, prova a uniformare a Long; in caso di fallimento, fallback a String
        Map<String, String> pdvMap;
        try {
            // chiave = idStoreLocator come String normalizzata (numero)
            pdvMap = pdvs.stream().collect(Collectors.toMap(
                    pdv -> String.valueOf(pdv.getIdStoreLocator()),
                    ResponseStoreLocatorDTO.Pdv::getCodicePdvCoop,
                    (a, b) -> a // in caso di duplicati mantieni il primo
            ));
        } catch (Exception e) {
            // fallback ultra sicuro (ad es. se getIdStoreLocator() può essere String)
            pdvMap = new HashMap<>();
            for (ResponseStoreLocatorDTO.Pdv pdv : pdvs) {
                String key = String.valueOf(pdv.getIdStoreLocator());
                pdvMap.putIfAbsent(key, pdv.getCodicePdvCoop());
            }
        }

        for (UserDto user : users) {
            String raw = user.getPdvId();
            if (raw == null) {
                continue;
            }
            String key = raw.trim();
            if (key.isEmpty()) {
                continue;
            }

            // Se il pdvId dell’utente è numerico con zeri iniziali, prova anche la chiave senza zeri
            String codice = pdvMap.get(key);
            if (codice == null && key.matches("^0+\\d+$")) {
                String noLeadingZeros = key.replaceFirst("^0+(\\d+)$", "$1");
                codice = pdvMap.get(noLeadingZeros);
            }
            if (codice != null) {
                user.setCodicePdv(codice);
            }
        }
    }

    public void enrichUserCodicePdv(List<UserDto> users) {
        if (users == null || users.isEmpty()) {
            return;
        }

        // Recupero (cacheata) la lista PDV
        List<ResponseStoreLocatorDTO.Pdv> pdvs = listPdvCodeDetailsAllCoop();
        if (pdvs == null || pdvs.isEmpty()) {
            return;
        }

        // Mappa idStoreLocator -> codicePdvCoop
        Map<String, String> pdvMap = pdvs.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        pdv -> String.valueOf(pdv.getIdStoreLocator()).trim(),
                        ResponseStoreLocatorDTO.Pdv::getCodicePdvCoop,
                        (a, b) -> a // in caso di duplicati tieni il primo
                ));

        for (UserDto user : users) {
            if (user == null) {
                continue;
            }
            String raw = user.getPdvId();
            if (raw == null) {
                continue;
            }

            String key = raw.trim();
            if (key.isEmpty()) {
                continue;
            }

            // 1° tentativo: chiave così com'è
            String codice = pdvMap.get(key);

            // 2° tentativo: se numerico con zeri iniziali, prova senza zeri
            if (codice == null && key.matches("^0+\\d+$")) {
                String noLeadingZeros = key.replaceFirst("^0+(\\d+)$", "$1");
                codice = pdvMap.get(noLeadingZeros);
            }

            // 3° tentativo opzionale: se è tutto numerico, normalizza a numero
            if (codice == null && key.matches("^\\d+$")) {
                String normalized = String.valueOf(Long.parseLong(key));
                codice = pdvMap.get(normalized);
            }

            if (codice != null) {
                user.setCodicePdv(codice);
            }
        }
    }

}
