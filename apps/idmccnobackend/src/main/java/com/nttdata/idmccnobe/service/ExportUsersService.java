package com.nttdata.idmccnobe.service;

import com.nttdata.idmccnobe.dao.OptinDao;
import com.nttdata.idmccnobe.dao.UserEntityDao;
import com.nttdata.idmccnobe.dto.UserDto;
import com.nttdata.idmccnobe.dto.UserEanCardDto;
import com.nttdata.idmccnobe.dto.UserExcelDto;
import com.nttdata.idmccnobe.dto.UserPrivacyDto;
import com.nttdata.idmccnobe.model.PrivacyOptin;
import com.nttdata.idmccnobe.model.UserEntity;
import com.nttdata.idmccnobe.util.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DelorenziVa
 */
@Service("ExportUsersService")
@Transactional
public class ExportUsersService extends AbstractService {

    @Autowired
    private UserEntityDao userEntityDao;

    @Autowired
    private StoreLocatorClientService storeLocatorClientService;

    @Autowired
    private OptinDao optinDao;
    
    @Autowired
    private UserService userService;
    
    public List<UserExcelDto> searchUsersToExport(
            String coopId,
            String emailVerified,
            String socio,
            Date registrationDateFrom,
            Date registrationDateTo,
            Date lastAppAccessDateFrom,
            Date lastAppAccessDateTo,
            boolean lastAppAccessDateIncludeNulls,
            Date lastEcommerceAccessDateFrom,
            Date lastEcommerceAccessDateTo,
            boolean lastEcommerceAccessDateIncludeNulls,
            Date lastCommunityAccessDateFrom,
            Date lastCommunityAccessDateTo,
            boolean lastCommunityAccessDateIncludeNulls,
            Date lastPortalAccessDateFrom,
            Date lastPortalAccessDateTo,
            boolean lastPortalAccessDateIncludeNulls,
            Date lastPortalNovaAccessDateFrom,
            Date lastPortalNovaAccessDateTo,
            boolean lastPortalNovaAccessDateIncludeNulls,
            Date lastModifyDateFrom,
            Date lastModifyDateTo,
            String pdvId) throws ParseException {
        long startQueryTime = System.currentTimeMillis();
        logger.info("exportUsers -> Inizio query");
        //List<UserEntity> users = userEntityDao.search(coopId, emailVerified, socio);
        List<UserDto> users = userEntityDao.searchSql(
                coopId,
                emailVerified,
                socio,
                registrationDateFrom,
                registrationDateTo,
                lastAppAccessDateFrom,
                lastAppAccessDateTo,
                lastAppAccessDateIncludeNulls,
                lastEcommerceAccessDateFrom,
                lastEcommerceAccessDateTo,
                lastEcommerceAccessDateIncludeNulls,
                lastCommunityAccessDateFrom,
                lastCommunityAccessDateTo,
                lastCommunityAccessDateIncludeNulls,
                lastPortalAccessDateFrom,
                lastPortalAccessDateTo,
                lastPortalAccessDateIncludeNulls,
                lastPortalNovaAccessDateFrom,
                lastPortalNovaAccessDateTo,
                lastPortalNovaAccessDateIncludeNulls,
                lastModifyDateFrom,
                lastModifyDateTo,
                pdvId);
        long stopQueryTime = System.currentTimeMillis();
        logger.info("exportUsers -> Fine query, tempo impiegato: " + (stopQueryTime - startQueryTime) / 1000 + "s");
        logger.info("exportUsers -> Utenti trovati: " + (users != null ? users.size() : "0"));
        List<UserExcelDto> resultList = new ArrayList<>();
        long startToDtoTime = System.currentTimeMillis();
        logger.info("exportUsers -> Inizio toDto");
        //for (UserEntity user : users) {

// Arricchimento codicePdv basato su pdvId/idStoreLocator
        storeLocatorClientService.enrichUserCodicePdv(users);

        List<PrivacyOptin> optinListLV = getOptinListLatestVersion(null, null, null);
        for (UserDto user : users) {
            resultList.add(toExcelDto(user,optinListLV,false));
        }
        long stopToDtoTime = System.currentTimeMillis();
        logger.info("exportUsers -> Fine toDto, tempo impiegato: " + (stopToDtoTime - startToDtoTime) / 1000 + "m");
        return resultList;
    }
    
    public List<UserExcelDto> getInactiveUsersToExcel(String application) throws ParseException{
        return convertListUserDtoToListUserExcelDto(userService.getInactiveUsers(application),false);
    }
    
    public List<UserExcelDto> getRemovedUsersToExcel(String coopId, String email) throws ParseException {
        return convertListUserDtoToListUserExcelDto(userService.getRemovedUser(coopId, email),true);
    }
    
    public List<UserEntity> search(String coopId, String emailVerified, String socio) {
        long startQueryTime = System.currentTimeMillis();
        logger.info("exportUsers -> Inizio query");
        List<UserEntity> users = userEntityDao.search(coopId, emailVerified, socio);
        long stopQueryTime = System.currentTimeMillis();
        logger.info("exportUsers -> Fine query, tempo impiegato: " + (stopQueryTime - startQueryTime) / 1000 + "s");
        return users;
    }
    
    public List<UserExcelDto> convertListUserDtoToListUserExcelDto (List<UserDto> users, boolean utentiCancellati) throws ParseException {
        List<UserExcelDto> resultList = new ArrayList<>();
        long startToDtoTime = System.currentTimeMillis(); 
        logger.info("convertListUserDtoToListUserExcelDto -> Inizio toDto");
        List<PrivacyOptin> optinListLV = getOptinListLatestVersion(null, null, null);
        for (UserDto user : users) {
            resultList.add(toExcelDto(user,optinListLV,utentiCancellati));
        }
        long stopToDtoTime = System.currentTimeMillis();  
        logger.info("convertListUserDtoToListUserExcelDto -> Fine toDto, tempo impiegato: " + (stopToDtoTime - startToDtoTime)/1000 + "s");
        return resultList;
    }
    
    public UserExcelDto toExcelDto(UserDto user, List<PrivacyOptin> optinListLV, boolean utentiCancellati) throws ParseException { 
        UserExcelDto userExcel = new UserExcelDto();
        //logger.info("exportUsers -> user toExcelDto: userId="+user.getId()+", name="+user.getFirstName()+", surname="+user.getLastName()+", email="+user.getEmail());
        userExcel.setUserId(user.getUserId());
        userExcel.setNome(user.getName());
        userExcel.setCognome(user.getSurname());
        userExcel.setEmail(user.getEmail());
        userExcel.setEmailVerificata(user.getEmailVerified());
        Date regDate = new Date(Long.parseLong(user.getRegDate()));
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
        String strDate = fmt.format(regDate);
        userExcel.setDataDiRegistrazione(strDate);
        userExcel.setPrimoAccessoDaAppCoop(user.getFirstAppAccessDate());
        userExcel.setUltimoAccessoDaAppCoop(user.getLastAppAccessDate());
        userExcel.setUtenteDisabilitatoSuApp(user.getUserDisableApp());
        userExcel.setPrimoAccessoDaEcommerce(user.getFirstEcommerceAccessDate());
        userExcel.setUltimoAccessoDaEcommerce(user.getLastEcommerceAccessDate());
        userExcel.setUtenteDisabilitatoSuEcommerce(user.getUserDisableEcommerce());
        userExcel.setPrimoAccessoDaCommunity(user.getFirstEventiInteressiAccessDate());
        userExcel.setUltimoAccessoDaCommunity(user.getLastEventiInteressiAccessDate());
        userExcel.setUtenteDisabilitatoSuCommunity(user.getUserDisableEventiInteressi());
        userExcel.setPrimoAccessoDaPortale(user.getFirstPortalAccessDate());
        userExcel.setUltimoAccessoDaPortale(user.getLastPortalAccessDate());
        userExcel.setUtenteDisabilitatoSuPortal(user.getUserDisablePortal());
        userExcel.setPrimoAccessoDaPortaleNova(user.getFirstPortalNovaAccessDate());
        userExcel.setUltimoAccessoDaPortaleNova(user.getLastPortalNovaAccessDate());
        userExcel.setUtenteDisabilitatoSuPortaleNova(user.getUserDisablePortalNova());
        userExcel.setUtenteCancellato(utentiCancellati ? "SI" : "NO");
        userExcel.setEmailVerificata(user.getEmailVerified());
        userExcel.setLastPrivacyUpdate(user.getLastPrivacyUpdate());
        userExcel.setStato(user.getStatus());
        List<UserEanCardDto> eanCards = user.getEanCards();
        String commaSeparatedEanCards = "";
        if (eanCards != null && !eanCards.isEmpty()) {
            List<String> distinctEanCards = new ArrayList<>();
            for (UserEanCardDto eanCard : eanCards) {
                distinctEanCards.add(eanCard.getEanCard());
            }
            commaSeparatedEanCards = String.join(", ", distinctEanCards);
        }
        userExcel.setTessereSocio(commaSeparatedEanCards);
        userExcel.setCooperativa(user.getCoopId());
        userExcel.setApplicazioneDiRegistrazione(user.getRegApplication());
        userExcel.setDataDiNascita(user.getBirthDate());
        userExcel.setSesso(user.getGender());
        userExcel.setType(user.getType());
        userExcel.setProvinciaResidenza(user.getResProvince() != null ? user.getResProvince().getName() : null);
        userExcel.setCittaResidenza(user.getResCity() != null ? user.getResCity().getName() : null);
        userExcel.setPdvId(user.getPdvId());
        userExcel.setCodicePdv(user.getCodicePdv());
        userExcel.setDataUltimaModifica(user.getLastModifyDate());
        List<UserPrivacyDto> userOptinList = user.getUserPrivacyList();
        String commaSeparatedOptins = "";
        if (userOptinList != null && !userOptinList.isEmpty()) {
            // privacies
            List<UserPrivacyDto> userCompleteOptinList = new ArrayList<>();
            for (PrivacyOptin optinObj : optinListLV) {
                UserPrivacyDto optinDto = new UserPrivacyDto();
                String id = optinObj.getId().toString();
                String coopId = optinObj.getPrivacy().getCooperativa().getId().toString();
                Integer privacyType = optinObj.getType();
                String userType = optinObj.getPrivacy().getUserType().toString();
                String latestVersion = optinObj.getVersion().toString();
                Integer ordine = optinObj.getOrdine();
                optinDto.setId(id);
                optinDto.setCoopId(coopId);
                optinDto.setUserType(userType);
                optinDto.setPrivacyType(privacyType);
                optinDto.setVersion(latestVersion);
                optinDto.setValue(null);
                optinDto.setOrdine(ordine);
                for (UserPrivacyDto optin : userOptinList) {
                    if (optinDto.getId().equals(optin.getId())) {
                        optinDto.setValue(optin.getValue());
                        break;
                    }
                }
                userCompleteOptinList.add(optinDto);
            }
            for (UserPrivacyDto optin : userCompleteOptinList) {
                String coopId = (optin.getCoopId() != null ? optin.getCoopId() : "");
                String id = optin.getId();
                Boolean value = optin.getValue();
                String version = (optin.getVersion() != null ? optin.getVersion() : "");
                if (id != null) {
                    switch (coopId) {
                        case "1":
                            // "Novacoop";
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 1 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_GENERICO)) {
                                userExcel.setConsensoProfilazioneNovacoop((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 2 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_GENERICO)) {
                                userExcel.setConsensoMarketingNovacoop((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 1 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_SOCI)) {
                                userExcel.setConsensoProfilazioneNovacoopSoci((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 2 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_SOCI)) {
                                userExcel.setConsensoMarketingNovacoopSoci((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 1 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_NON_SOCI)) {
                                userExcel.setConsensoProfilazioneNovacoopNonSoci((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 2 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_NON_SOCI)) {
                                userExcel.setConsensoMarketingNovacoopNonSoci((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            break;
                        case "2":
                            // "Coop Liguria";
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 1 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_GENERICO)) {
                                userExcel.setConsensoProfilazioneCoopLiguria((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 2 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_GENERICO)) {
                                userExcel.setConsensoMarketingCoopLiguria((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 1 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_SOCI)) {
                                userExcel.setConsensoProfilazioneCoopLiguriaSoci((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 2 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_SOCI)) {
                                userExcel.setConsensoMarketingCoopLiguriaSoci((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 1 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_NON_SOCI)) {
                                userExcel.setConsensoProfilazioneCoopLiguriaNonSoci((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 2 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_NON_SOCI)) {
                                userExcel.setConsensoMarketingCoopLiguriaNonSoci((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            break;
                        case "3":
                            // "Coop Lombardia";
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 1 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_GENERICO)) {
                                userExcel.setConsensoProfilazioneCoopLombardia((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 2 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_GENERICO)) {
                                userExcel.setConsensoMarketingCoopLombardia((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 1 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_SOCI)) {
                                userExcel.setConsensoProfilazioneCoopLombardiaSoci((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 2 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_SOCI)) {
                                userExcel.setConsensoMarketingCoopLombardiaSoci((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 1 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_NON_SOCI)) {
                                userExcel.setConsensoProfilazioneCoopLombardiaNonSoci((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            if (optin.getPrivacyType() != null && optin.getPrivacyType() == 2 && optin.getUserType() != null && optin.getUserType().equals(Constants.PRIVACY_USER_TYPE_NON_SOCI)) {
                                userExcel.setConsensoMarketingCoopLombardiaNonSoci((value != null ? (value ? "Accettata" : "Negata") : "Non espressa"));
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return userExcel;
    }     
    
    public List<PrivacyOptin> getOptinListLatestVersion(String coopId, String privacyId, String optinId) {
        List<PrivacyOptin> result = new ArrayList<>();
        List<PrivacyOptin> optinList = optinDao.list(coopId, privacyId, optinId);
        Map<Integer, Integer> privacyLatestVersions = getOptinLatestVersions(optinList);
        for (PrivacyOptin optinObj : optinList) {
            if (Objects.equals(optinObj.getVersion(), privacyLatestVersions.get(optinObj.getPrivacy().getId()))) {
                result.add(optinObj);
            }
        }
        return result;
    }

    public Map<Integer, Integer> getOptinLatestVersions(List<PrivacyOptin> optinList) {
        Map<Integer, Integer> latestVersions = new HashMap<>();
        optinList = (optinList != null ? optinList : optinDao.list(null, null, null));
        if (optinList != null && optinList.size() > 0) {
            for (PrivacyOptin optinObj : optinList) {
                Integer privacyId = optinObj.getPrivacy().getId();
                if (!latestVersions.containsKey(privacyId)) {
                    latestVersions.put(privacyId, null);
                }
                if (latestVersions.get(privacyId) == null ? true : optinObj.getVersion() > latestVersions.get(privacyId)) {
                    latestVersions.replace(privacyId, optinObj.getVersion());
                }
            }
        }
        return latestVersions;
    }
    
}
