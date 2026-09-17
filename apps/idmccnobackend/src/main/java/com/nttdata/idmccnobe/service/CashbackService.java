/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.idmccnobe.dao.ParameterBeDao;
import com.nttdata.idmccnobe.dto.AnagraficaCashbackRequest;
import com.nttdata.idmccnobe.dto.AnagraficaCashbackResDto;
import com.nttdata.idmccnobe.dto.CashbackFormatted;
import com.nttdata.idmccnobe.dto.CashbackFromDb;
import com.nttdata.idmccnobe.dto.DeleteCashbackRequest;
import com.nttdata.idmccnobe.dto.DeleteCashbackResDto;
import com.nttdata.idmccnobe.dto.FindAccFruProductLinesRequest;
import com.nttdata.idmccnobe.dto.FindPdvIdSelectedRequest;
import com.nttdata.idmccnobe.dto.FindPdvIdSelectedResDto;
import com.nttdata.idmccnobe.dto.FindProductLinesResDto;
import com.nttdata.idmccnobe.dto.ProductLineDto;
import com.nttdata.idmccnobe.dto.RetrievePdvForCoopIdRequest;
import com.nttdata.idmccnobe.dto.SaveCashbackRequest;
import com.nttdata.idmccnobe.dto.SaveCashbackResDto;
import com.nttdata.idmccnobe.dto.SearchCashbackRequest;
import com.nttdata.idmccnobe.dto.SearchCashbackResDto;
import com.nttdata.idmccnobe.dto.SelectedPdvForDropdown;
import com.nttdata.idmccnobe.dto.UpdateCashbackPdvRequest;
import com.nttdata.idmccnobe.dto.UpdateCashbackPdvResDto;
import com.nttdata.idmccnobe.util.Constants;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.annotation.PostConstruct;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author BernardisMa
 */
@Service("CashbackService")
@Transactional
public class CashbackService extends AbstractService {

    private static final int CONNECT_TIMEOUT_MS = 10_000;
    private static final int SOCKET_TIMEOUT_MS = 60_000;

    @Autowired
    private ParameterBeService parameterBeService;

    @Autowired
    private UtilsService utilsService;

    @PostConstruct
    public void configureHttpClient() {
        Unirest.config()
                .connectTimeout(CONNECT_TIMEOUT_MS)
                .socketTimeout(SOCKET_TIMEOUT_MS);
    }

//    URL PER SVILUPPO LOCAL
//    String idmCcnoHost = "https://apim-devtest-team-ntt-001.azure-api.net";
//    String idmCcnoBaseUrl = "IdmCCNOProxy";
    public SearchCashbackResDto searchCashback(SearchCashbackRequest searchCashbackRequest) throws IOException {
        SearchCashbackResDto searchCashbackResDto = new SearchCashbackResDto();
        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String backendBaseUrl = Constants.IDM_CCNO_BACKEND_BASE_URL;
        String cashbackBaseUrl = Constants.IDM_CCNO_CASHBACK_SERVICE_BASE_URL;
        String searchCashabackUrl = Constants.IDM_CCNO_SEARCH_USER_SERVICE;

        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + backendBaseUrl + "/" + cashbackBaseUrl + "/" + searchCashabackUrl;

//        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url + " | "
//                + "parametri: id -> " + searchCashbackRequest.getId()+ ", stato -> " + searchCashbackRequest.getStatoCashback()+ ", titolo -> " + searchCashbackRequest.getTitolo());
        // chiamata POST con Content-type = application/x-www-form-urlencoded
        HttpResponse<SearchCashbackResDto> response = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(searchCashbackRequest)
                .asObject(SearchCashbackResDto.class);
        validateResponse(response, "ricerca Cashback");
        searchCashbackResDto = response.getBody();

        return searchCashbackResDto;
    }

    public CashbackFormatted anagraficaCashback(String id) throws IOException {
        AnagraficaCashbackResDto anagraficaCashbackResDto = new AnagraficaCashbackResDto();
        AnagraficaCashbackRequest anagraficaCashbackRequest = new AnagraficaCashbackRequest(id);
        CashbackFromDb cashbackFromDb = new CashbackFromDb();
        CashbackFormatted finalResult = new CashbackFormatted();
        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String backendBaseUrl = Constants.IDM_CCNO_BACKEND_BASE_URL;
        String cashbackBaseUrl = Constants.IDM_CCNO_CASHBACK_SERVICE_BASE_URL;
        String anagraficaUrl = Constants.IDM_CCNO_CASHBACK_ANAGRAFICA_SERVICE_BASE_URL;

        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + backendBaseUrl + "/" + cashbackBaseUrl + "/" + anagraficaUrl;

        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url + " | " + "parametri: id -> " + id);

        // chiamata POST con Content-type = application/x-www-form-urlencoded
        HttpResponse<JsonNode> jsonResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(anagraficaCashbackRequest)
                .asJson();

        validateResponse(jsonResponse, "anagrafica Cashback id=" + id);
        JsonNode jsonNode = jsonResponse.getBody();
        String resultRes = jsonNode.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        anagraficaCashbackResDto = objectMapper.readValue(resultRes, AnagraficaCashbackResDto.class);
        if (anagraficaCashbackResDto.getPayload() == null
                || anagraficaCashbackResDto.getPayload().getCashback() == null) {
            throw new IOException("Risposta anagrafica Cashback incompleta per id=" + id);
        }
        cashbackFromDb = anagraficaCashbackResDto.getPayload().getCashback();
        this.formatFromDbCashback(cashbackFromDb, finalResult);
        return finalResult;

    }

    public void formatFromDbCashback(CashbackFromDb cashbackFromDb, CashbackFormatted cashbackFormatted) {
        cashbackFormatted.setCashbackid(cashbackFromDb.getCashbackid());
        cashbackFormatted.setCashbacktitle(cashbackFromDb.getCashbacktitle());
        cashbackFormatted.setCashbackdescription(cashbackFromDb.getCashbackdescription());
        cashbackFormatted.setCashbackvalue(cashbackFromDb.getCashbackvalue());
        cashbackFormatted.setStatus(cashbackFromDb.getStatus());
        cashbackFormatted.setAccumulationdescription(cashbackFromDb.getAccumulationdescription());
        cashbackFormatted.setAccumulationtype(cashbackFromDb.getAccumulationtype());
        cashbackFormatted.setAccumulationflyerpdflink(cashbackFromDb.getAccumulationflyerpdflink());
        cashbackFormatted.setAccumulationflyerpdfimageurl(cashbackFromDb.getAccumulationflyerpdfimageurl());
        cashbackFormatted.setFruitiondescription(cashbackFromDb.getFruitiondescription());
        cashbackFormatted.setFruitiontype(cashbackFromDb.getFruitiontype());
        cashbackFormatted.setFruitionflyerpdflink(cashbackFromDb.getFruitionflyerpdflink());
        cashbackFormatted.setFruitionflyerpdfimageurl(cashbackFromDb.getFruitionflyerpdfimageurl());
        cashbackFormatted.setConditionsdescription(cashbackFromDb.getConditionsdescription());
        cashbackFormatted.setConditionsvaliditydescription(cashbackFromDb.getConditionsvaliditydescription());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Formattazione delle date nel formato desiderato
        cashbackFormatted.setCashbackpublicationstartdate(formatDate(cashbackFromDb.getCashbackpublicationstartdate(), dateFormat));
        cashbackFormatted.setCashbackpublicationenddate(formatDate(cashbackFromDb.getCashbackpublicationenddate(), dateFormat));
        cashbackFormatted.setAccumulationstartdate(formatDate(cashbackFromDb.getAccumulationstartdate(), dateFormat));
        cashbackFormatted.setAccumulationenddate(formatDate(cashbackFromDb.getAccumulationenddate(), dateFormat));
        cashbackFormatted.setFruitionstartdate(formatDate(cashbackFromDb.getFruitionstartdate(), dateFormat));
        cashbackFormatted.setFruitionenddate(formatDate(cashbackFromDb.getFruitionenddate(), dateFormat));
    }

    private String formatDate(Date date, SimpleDateFormat dateFormat) {
        return date != null ? dateFormat.format(date) : null;
    }

    private void validateResponse(HttpResponse<?> response, String operation) throws IOException {
        if (response == null) {
            throw new IOException("Nessuna risposta ricevuta durante " + operation);
        }
        if (response.getStatus() < 200 || response.getStatus() >= 300) {
            throw new IOException("HTTP " + response.getStatus() + " durante " + operation);
        }
        if (response.getBody() == null) {
            throw new IOException("Risposta vuota durante " + operation);
        }
    }

    public List<ProductLineDto> findProductLines() {
        FindProductLinesResDto findProductLinesResDto = new FindProductLinesResDto();
        List<ProductLineDto> listProductLines = new ArrayList<>();
        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String backendBaseUrl = Constants.IDM_CCNO_BACKEND_BASE_URL;
        String cashbackBaseUrl = Constants.IDM_CCNO_CASHBACK_SERVICE_BASE_URL;
        String findProductLinesUrl = Constants.IDM_CCNO_CASHBACK_PRODUCT_LINES;

        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + backendBaseUrl + "/" + cashbackBaseUrl + "/" + findProductLinesUrl;

//        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url);
        // chiamata POST con Content-type = application/x-www-form-urlencoded
        findProductLinesResDto = Unirest.post(url)
                .header("Content-Type", "application/json")
                .asObject(FindProductLinesResDto.class)
                .getBody();

        if (findProductLinesResDto != null && findProductLinesResDto.getPayload() != null) {
            listProductLines = findProductLinesResDto.getPayload().getProductLines();
        }

        return listProductLines;
    }

    public List<ProductLineDto> findAccFruProductLinesResponse(int id, String type) {
        FindProductLinesResDto findProductLinesResDto = new FindProductLinesResDto();
        FindAccFruProductLinesRequest findAccFruProductLinesRequest = new FindAccFruProductLinesRequest();
        findAccFruProductLinesRequest.setId(id);
        findAccFruProductLinesRequest.setType(type);
        List<ProductLineDto> listProductLines = new ArrayList<>();
        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String backendBaseUrl = Constants.IDM_CCNO_BACKEND_BASE_URL;
        String cashbackBaseUrl = Constants.IDM_CCNO_CASHBACK_SERVICE_BASE_URL;
        String findAccFruProductLinesResponseUrl = Constants.IDM_CCNO_CASHBACK_FIND_ACC_FRU_LINES;

        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + backendBaseUrl + "/" + cashbackBaseUrl + "/" + findAccFruProductLinesResponseUrl;

//        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url+ " | "
//                + "parametri: id -> " + id+ ", type -> " + type);
        // chiamata POST con Content-type = application/x-www-form-urlencoded
        findProductLinesResDto = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(findAccFruProductLinesRequest)
                .asObject(FindProductLinesResDto.class)
                .getBody();

        listProductLines = findProductLinesResDto.getPayload().getProductLines();

        return listProductLines;
    }

    public Map<String, Integer> createSelectionMap(List<ProductLineDto> productLines, List<ProductLineDto> selectedLines) {
        Map<String, Integer> selectionMap = new HashMap<>();
        // Popola la mappa con tutti i nomi delle linee di prodotto da productLines e imposta il valore iniziale a 0
        for (ProductLineDto productLine : productLines) {
            selectionMap.put(productLine.getName(), 0);
        }
        // Imposta a 1 i valori nella mappa per le linee di prodotto presenti in selectedLines
        if (selectedLines != null && !selectedLines.isEmpty()) {
            for (ProductLineDto selectedLine : selectedLines) {
                String name = selectedLine.getName();
                if (selectionMap.containsKey(name)) {
                    selectionMap.put(name, 1);
                }
            }
        }

        return selectionMap;
    }

    public List<String> findPdvIdSelected(String coopId, int cashbackId) {
        FindPdvIdSelectedResDto findPdvIdSelectedResDto = new FindPdvIdSelectedResDto();
        FindPdvIdSelectedRequest findPdvIdSelectedRequest = new FindPdvIdSelectedRequest();
        findPdvIdSelectedRequest.setCoopId(coopId);
        findPdvIdSelectedRequest.setCashbackId(Integer.toString(cashbackId));
        List<String> result = new ArrayList<>();
        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String backendBaseUrl = Constants.IDM_CCNO_BACKEND_BASE_URL;
        String cashbackBaseUrl = Constants.IDM_CCNO_CASHBACK_SERVICE_BASE_URL;
        String findPdvIdSelectedUrl = Constants.IDM_CCNO_CASHBACK_FIND_PDV_ID_SELECTED;

        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + backendBaseUrl + "/" + cashbackBaseUrl + "/" + findPdvIdSelectedUrl;

//        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url+ " | "
//                + "parametri: coopId -> " + coopId+ ", cashbackId -> " + cashbackId);
        // chiamata POST con Content-type = application/x-www-form-urlencoded
        findPdvIdSelectedResDto = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(findPdvIdSelectedRequest)
                .asObject(FindPdvIdSelectedResDto.class)
                .getBody();

        result = findPdvIdSelectedResDto.getPayload().getSelectedPdvId();

        return result;
    }

    @Cacheable(cacheNames = "pdvForCoopIds", cacheManager = "springCM")
    public List<SelectedPdvForDropdown> retrievePdvForCoopId(int coopId) throws JsonProcessingException {
        List<SelectedPdvForDropdown> responseList = new ArrayList<>();
        RetrievePdvForCoopIdRequest retrievePdvForCoopIdRequest = new RetrievePdvForCoopIdRequest();
        List<Integer> listCoop = new ArrayList<>();
        listCoop.add(coopId);
        retrievePdvForCoopIdRequest.setCoopIdList(listCoop);

        String apimEndpointUrl = parameterBeService.getParameterValueByName(Constants.APIM_ENDPOINT);
        String storelocatorUrl = Constants.APIM_STORELOCATORE_URL;
        String getPdvListByCoopIds = Constants.APIM_CCNO_CASHBACK_GET_PDV_LIST_BY_COOP_IDS;

        String url = apimEndpointUrl + "/" + storelocatorUrl + "/" + getPdvListByCoopIds;

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RestTemplate restTemplate = utilsService.proxyRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(retrievePdvForCoopIdRequest), headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            String responseBody = response.getBody();

            JSONObject jsonNodeObject = new JSONObject(responseBody);
            JSONObject headerJson = jsonNodeObject.getJSONObject("header");
            if (!headerJson.isEmpty()) {
                Object resultValue = headerJson.get("result");
                String resultString = (String) resultValue;
                if (resultString.equalsIgnoreCase("OK")) {
                    JSONObject payloadJson = jsonNodeObject.getJSONObject("payload");
                    if (!payloadJson.isEmpty()) {
                        JSONArray storesJson = payloadJson.getJSONArray("stores");
                        if (!storesJson.isEmpty()) {
                            for (int i = 0; i < storesJson.length(); i++) {
                                JSONObject store = storesJson.getJSONObject(i);
                                String storeId = store.getString("id");
                                String name = store.getString("name");
                                String city = store.getString("city");
                                if (storeId != null && name != null && city != null) {
                                    String value = name + " // " + city;
                                    SelectedPdvForDropdown toAdd = new SelectedPdvForDropdown(storeId, "0", value);
                                    responseList.add(toAdd);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("CashbackService - retrievePdvForCoopId() - " + e.getMessage());
        }

        return responseList;
    }

    public List<SelectedPdvForDropdown> updateSelectedStatus(List<SelectedPdvForDropdown> pdvList, List<String> findPdvIdSelected) {
        for (SelectedPdvForDropdown pdv : pdvList) {
            if (findPdvIdSelected != null) {
                if (findPdvIdSelected.contains(pdv.getId())) {
                    pdv.setSelected("1");
                } else {
                    pdv.setSelected("0");
                }
            } else {
                pdv.setSelected("0");
            }
        }
        return pdvList;
    }

    public SaveCashbackResDto updateCashback(SaveCashbackRequest saveCashbackRequest) {
        SaveCashbackResDto updateCashbackResDto = new SaveCashbackResDto();
        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String backendBaseUrl = Constants.IDM_CCNO_BACKEND_BASE_URL;
        String cashbackBaseUrl = Constants.IDM_CCNO_CASHBACK_SERVICE_BASE_URL;
        String updateCashbackUrl = Constants.IDM_CCNO_CASHBACK_UPDATE;

        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + backendBaseUrl + "/" + cashbackBaseUrl + "/" + updateCashbackUrl;

//        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url + " | "
//                + "parametri: id -> "  + saveCashbackRequest.getId()
//                + ", cashbackTitle -> " + saveCashbackRequest.getCashbackTitle()
//                + ", cashbackDescription -> " + saveCashbackRequest.getCashbackDescription()
//                + ", cashbackValue -> " + saveCashbackRequest.getCashbackValue()
//                + ", status -> " + saveCashbackRequest.getStatus()
//                + ", cashbackPublicationStartDate -> " + saveCashbackRequest.getCashbackPublicationStartDate()
//                + ", cashbackPublicationEndDate -> " + saveCashbackRequest.getCashbackPublicationEndDate()
//                + ", conditionsDescription -> " + saveCashbackRequest.getConditionsDescription()
//                + ", conditionsValidityDescription -> " + saveCashbackRequest.getConditionsValidityDescription()
//                + ", accumulationDescription -> " + saveCashbackRequest.getAccumulationDescription()
//                + ", accumulationType -> " + saveCashbackRequest.getAccumulationType()
//                + ", accumulationStartDate -> " + saveCashbackRequest.getAccumulationStartDate()
//                + ", accumulationEndDate -> " + saveCashbackRequest.getAccumulationEndDate()
//                + ", accumulationFlyerPdfLink -> " + saveCashbackRequest.getAccumulationFlyerPdfLink()
//                + ", accumulationFlyerPdfImageUrl -> " + saveCashbackRequest.getAccumulationFlyerPdfImageUrl()
//                + ", accumulationProductLines -> " + saveCashbackRequest.getAccumulationProductLines()
//                + ", fruitionDescription -> " + saveCashbackRequest.getFruitionDescription()
//                + ", fruitionType -> " + saveCashbackRequest.getFruitionType()
//                + ", fruitionStartDate -> " + saveCashbackRequest.getFruitionStartDate()
//                + ", fruitionEndDate -> " + saveCashbackRequest.getFruitionEndDate()
//                + ", fruitionFlyerPdfLink -> " + saveCashbackRequest.getFruitionFlyerPdfLink()
//                + ", fruitionFlyerPdfImageUrl -> " + saveCashbackRequest.getFruitionFlyerPdfImageUrl()
//                + ", fruitionProductLines -> " + saveCashbackRequest.getFruitionProductLines()
//        );
        // chiamata POST con Content-type = application/x-www-form-urlencoded
        updateCashbackResDto = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(saveCashbackRequest)
                .asObject(SaveCashbackResDto.class)
                .getBody();

        return updateCashbackResDto;
    }

    public UpdateCashbackPdvResDto updateCashbackPdv(UpdateCashbackPdvRequest updateCashbackPdvRequest) {
        UpdateCashbackPdvResDto updateCashbackPdvResDto = new UpdateCashbackPdvResDto();
        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String backendBaseUrl = Constants.IDM_CCNO_BACKEND_BASE_URL;
        String cashbackBaseUrl = Constants.IDM_CCNO_CASHBACK_SERVICE_BASE_URL;
        String updateCashbackPdvUrl = Constants.IDM_CCNO_CASHBACK_UPDATE_PDV;

        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + backendBaseUrl + "/" + cashbackBaseUrl + "/" + updateCashbackPdvUrl;

//        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url + " | "
//                + "parametri: id -> "  + updateCashbackPdvRequest.getCashbackId()
//                + ", allSelectedNovacoop -> " + updateCashbackPdvRequest.getAllSelectedNovacoop()
//                + ", selectedPdvIdsNovacoop -> " + updateCashbackPdvRequest.getSelectedPdvIdsNovacoop()
//                + ", allSelectedLiguria -> " + updateCashbackPdvRequest.getAllSelectedLiguria()
//                + ", selectedPdvIdsLiguria -> " + updateCashbackPdvRequest.getSelectedPdvIdsLiguria()
//                + ", allSelectedLombardia -> " + updateCashbackPdvRequest.getAllSelectedLombardia()
//                + ", selectedPdvIdsLombardia -> " + updateCashbackPdvRequest.getSelectedPdvIdsLombardia()
//        );
        // chiamata POST con Content-type = application/x-www-form-urlencoded
        updateCashbackPdvResDto = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(updateCashbackPdvRequest)
                .asObject(UpdateCashbackPdvResDto.class)
                .getBody();

        return updateCashbackPdvResDto;
    }

    public DeleteCashbackResDto deleteCashback(String id) {
        DeleteCashbackResDto deleteCashbackResDto = new DeleteCashbackResDto();
        DeleteCashbackRequest deleteCashbackRequest = new DeleteCashbackRequest();
        deleteCashbackRequest.setId(id);
        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String backendBaseUrl = Constants.IDM_CCNO_BACKEND_BASE_URL;
        String cashbackBaseUrl = Constants.IDM_CCNO_CASHBACK_SERVICE_BASE_URL;
        String deleteCashbackUrl = Constants.IDM_CCNO_CASHBACK_DELETE;

        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + backendBaseUrl + "/" + cashbackBaseUrl + "/" + deleteCashbackUrl;

//        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url + " | " + "parametri: id -> "  + deleteCashbackRequest.getId());
        // chiamata POST con Content-type = application/x-www-form-urlencoded
        deleteCashbackResDto = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(deleteCashbackRequest)
                .asObject(DeleteCashbackResDto.class)
                .getBody();

        return deleteCashbackResDto;
    }

    public SaveCashbackResDto saveCashback(SaveCashbackRequest saveCashbackRequest) {
        SaveCashbackResDto saveCashbackResDto = new SaveCashbackResDto();
        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String backendBaseUrl = Constants.IDM_CCNO_BACKEND_BASE_URL;
        String cashbackBaseUrl = Constants.IDM_CCNO_CASHBACK_SERVICE_BASE_URL;
        String saveCashbackUrl = Constants.IDM_CCNO_CASHBACK_SAVE;

        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + backendBaseUrl + "/" + cashbackBaseUrl + "/" + saveCashbackUrl;

//        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url + " | "
//                + "parametri: cashbackTitle -> " + saveCashbackRequest.getCashbackTitle()
//                + ", cashbackDescription -> " + saveCashbackRequest.getCashbackDescription()
//                + ", cashbackValue -> " + saveCashbackRequest.getCashbackValue()
//                + ", status -> " + saveCashbackRequest.getStatus()
//                + ", cashbackPublicationStartDate -> " + saveCashbackRequest.getCashbackPublicationStartDate()
//                + ", cashbackPublicationEndDate -> " + saveCashbackRequest.getCashbackPublicationEndDate()
//                + ", conditionsDescription -> " + saveCashbackRequest.getConditionsDescription()
//                + ", conditionsValidityDescription -> " + saveCashbackRequest.getConditionsValidityDescription()
//                + ", accumulationDescription -> " + saveCashbackRequest.getAccumulationDescription()
//                + ", accumulationType -> " + saveCashbackRequest.getAccumulationType()
//                + ", accumulationStartDate -> " + saveCashbackRequest.getAccumulationStartDate()
//                + ", accumulationEndDate -> " + saveCashbackRequest.getAccumulationEndDate()
//                + ", accumulationFlyerPdfLink -> " + saveCashbackRequest.getAccumulationFlyerPdfLink()
//                + ", accumulationFlyerPdfImageUrl -> " + saveCashbackRequest.getAccumulationFlyerPdfImageUrl()
//                + ", accumulationProductLines -> " + saveCashbackRequest.getAccumulationProductLines()
//                + ", fruitionDescription -> " + saveCashbackRequest.getFruitionDescription()
//                + ", fruitionType -> " + saveCashbackRequest.getFruitionType()
//                + ", fruitionStartDate -> " + saveCashbackRequest.getFruitionStartDate()
//                + ", fruitionEndDate -> " + saveCashbackRequest.getFruitionEndDate()
//                + ", fruitionFlyerPdfLink -> " + saveCashbackRequest.getFruitionFlyerPdfLink()
//                + ", fruitionFlyerPdfImageUrl -> " + saveCashbackRequest.getFruitionFlyerPdfImageUrl()
//                + ", fruitionProductLines -> " + saveCashbackRequest.getFruitionProductLines()
//        );
        // chiamata POST con Content-type = application/x-www-form-urlencoded
        saveCashbackResDto = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(saveCashbackRequest)
                .asObject(SaveCashbackResDto.class)
                .getBody();

        return saveCashbackResDto;
    }

    public String getStatus(CashbackFormatted cashback) throws ParseException {
        Date today = Calendar.getInstance().getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date publicationStartDate = sdf.parse(cashback.getCashbackpublicationstartdate());
        Date accumulationStartDate = sdf.parse(cashback.getAccumulationstartdate());
        Date accumulationEndDate = sdf.parse(cashback.getAccumulationenddate());
        Date fruitionStartDate = sdf.parse(cashback.getFruitionstartdate());
        Date fruitionEndDate = sdf.parse(cashback.getFruitionenddate());

        // Setta ore, minuti, secondi e millisecondi a zero per la data di oggi
        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.setTime(today);
        todayCalendar.set(Calendar.HOUR_OF_DAY, 0);
        todayCalendar.set(Calendar.MINUTE, 0);
        todayCalendar.set(Calendar.SECOND, 0);
        todayCalendar.set(Calendar.MILLISECOND, 0);
        today = todayCalendar.getTime();

        String status = "";
        if (publicationStartDate.compareTo(today) <= 0
                && accumulationStartDate.compareTo(today) > 0) {
            status = "PUBBLICAZIONE";
        } else if (accumulationStartDate.compareTo(today) <= 0
                && accumulationEndDate.compareTo(today) >= 0) {
            status = "ACCUMULO";
        } else if (accumulationEndDate.compareTo(today) < 0
                && fruitionStartDate.compareTo(today) > 0) {
            status = "ATTESA (W)";
        } else if (fruitionStartDate.compareTo(today) <= 0
                && fruitionEndDate.compareTo(today) >= 0) {
            status = "FRUIZIONE";
        } else if (fruitionEndDate.compareTo(today) < 0) {
            status = "TERMINATO";
        }

        return status;
    }

}
