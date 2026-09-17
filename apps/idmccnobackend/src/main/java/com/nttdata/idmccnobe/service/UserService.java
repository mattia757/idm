package com.nttdata.idmccnobe.service;

import com.nttdata.idmccnobe.dao.UserEntityDao;
import com.nttdata.idmccnobe.dto.CommonDto;
import com.nttdata.idmccnobe.dto.RemoveUserDto;
import com.nttdata.idmccnobe.dto.UserDto;
import com.nttdata.idmccnobe.dto.UserPrivacyDto;
import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.dto.UserRequest;
import com.nttdata.idmccnobe.dto.UserResDto;
import com.nttdata.idmccnobe.dto.UserResPrivacyDto;
import com.nttdata.idmccnobe.dto.UserResPrivacyOptin;
import com.nttdata.idmccnobe.dto.UserSearchResDto;
import com.nttdata.idmccnobe.util.CommonUtils;
import com.nttdata.idmccnobe.util.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DelorenziVa
 */
@Service("UserService")
@Transactional
public class UserService extends AbstractService {

    @Autowired
    private ParameterBeService parameterBeService;
    
    @Autowired
    private UserEntityDao userEntityDao;
        
    @Autowired
    private ParametriService parametriService;

    @Autowired
    private StoreLocatorClientService storeLocatorClientService;

    @Autowired
    private PartecipazioneInteresseService partecipazioneInteresseService;

    // chiamata a servizio IdmCCNO
    public UserSearchResDto searchUser(UserRequest userRequest) {
        UserSearchResDto userSearchResDto = new UserSearchResDto();

        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String backendBaseUrl = Constants.IDM_CCNO_BACKEND_BASE_URL;
        String userBaseUrl = Constants.IDM_CCNO_USER_SERVICE_BASE_URL;
        String searchUserUrl = Constants.IDM_CCNO_SEARCH_USER_SERVICE;

        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + backendBaseUrl + "/" + userBaseUrl + "/" + searchUserUrl;

        try {
            userSearchResDto = Unirest.post(url)
                    .header("Content-Type", "application/json")
                    .body(userRequest)
                    .asObject(UserSearchResDto.class)
                    .getBody();

            if (userSearchResDto == null) {
                userSearchResDto = new UserSearchResDto();
            }
        } catch (Exception ex) {
            logger.info("Errore chiamata da IdmBackend a IdmCCNO: " + url + " | parametri: " + userRequest.getEanCard() + ", " + userRequest.getEmail() + ", " + userRequest.getName() + ", " + userRequest.getSurname() + ", " + userRequest.getUserId() + " | errore: " + ex);
            // restituisco DTO vuoto per evitare NPE a valle
            userSearchResDto = new UserSearchResDto();
        }
// Arricchimento codicePdv basato su pdvId/idStoreLocator
        storeLocatorClientService.enrichUserCodicePdv(userSearchResDto);
        return userSearchResDto;
    }

    // chiamata a servizio IdmCCNO
    public RemoveUserDto removeUser(String userId, String deletionUser){
        RemoveUserDto removeUserDto = new RemoveUserDto();
        // chiamata a IdmCCNO
        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String backendBaseUrl = Constants.IDM_CCNO_BACKEND_BASE_URL;
        String userBaseUrl = Constants.IDM_CCNO_USER_SERVICE_BASE_URL;
        String removeUserUrl = Constants.IDM_CCNO_REMOVE_USER_SERVICE;
        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + backendBaseUrl+ "/" + userBaseUrl + "/" + removeUserUrl;
//        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url + " | parametri: " + userId);
        removeUserDto = Unirest.post(url)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .field("userId", userId)
            .field("deletionUser", deletionUser)
            .asObject(RemoveUserDto.class)
            .getBody();

        if (removeUserDto != null
                && removeUserDto.getHeader() != null
                && CommonDto.ACK_OK.equals(removeUserDto.getHeader().getResult())) {
            partecipazioneInteresseService.deletePartecipazioniByUserId(userId);
        }

        return removeUserDto;
    }

    // recupero utente tramite userId
    public UserResDto getUser(UserProfile userProfile, String userId) throws ParseException {
        UserRequest userRequest = new UserRequest();
        userRequest.setUserId(userId);
        UserSearchResDto userSearchResDto = searchUser(userRequest);  // cerco l'utente per userId
        if (userSearchResDto == null
                || userSearchResDto.getPayload() == null
                || userSearchResDto.getPayload().getUsers() == null
                || userSearchResDto.getPayload().getUsers().isEmpty()
                || userSearchResDto.getPayload().getUsers().get(0) == null) {
            logger.warn("Utente non trovato o risposta non valida da IdmCCNO: userId=" + userId);
            return null;
        }
        UserDto user = userSearchResDto.getPayload().getUsers().get(0); // recupero l'utente dall'oggetto in risposta
        UserResDto userRes = createUserObj(user); // creo l'oggetto utente di riposta
        
        // Arricchimento codicePdv basato su pdvId/idStoreLocator
        storeLocatorClientService.enrichUserCodicePdv(userSearchResDto);
        
        return userRes;
    }

    // creazione oggetto User
    public UserResDto createUserObj(UserDto user) throws ParseException {
        UserResDto userRes = new UserResDto(); // istanzio il mio oggetto user di risposta
        // eanCard
        userRes.setEanCards(user.getEanCards());

        // privacy
        // creo la lista degli oggetti privacy da tornare
        List<UserResPrivacyDto> userPrivacyObjList = new ArrayList<>();

        // creo un oggetto privacy per ogni cooperativa
        UserResPrivacyDto userPrivacyObjNovacoop = new UserResPrivacyDto();
        userPrivacyObjNovacoop.setCoop(Constants.COOP_NOVACOOP);
        // creo la lista di optin per ogni cooperativa
        List<UserResPrivacyOptin> privacyOptinsNcList = new ArrayList<>();

        UserResPrivacyDto userPrivacyObjLombardia = new UserResPrivacyDto();
        userPrivacyObjLombardia.setCoop(Constants.COOP_LOMBARDIA);
        List<UserResPrivacyOptin> privacyOptinsLoList = new ArrayList<>();

        UserResPrivacyDto userPrivacyObjLiguria = new UserResPrivacyDto();
        userPrivacyObjLiguria.setCoop(Constants.COOP_LIGURIA);
        List<UserResPrivacyOptin> privacyOptinsLigList = new ArrayList<>();

        UserResPrivacyDto userPrivacyObjNovacoopSoci = new UserResPrivacyDto();
        userPrivacyObjNovacoopSoci.setCoop(Constants.COOP_NOVACOOP_SOCI);
        List<UserResPrivacyOptin> privacyOptinsNcSociList = new ArrayList<>();

        UserResPrivacyDto userPrivacyObjLombardiaSoci = new UserResPrivacyDto();
        userPrivacyObjLombardiaSoci.setCoop(Constants.COOP_LOMBARDIA_SOCI);
        List<UserResPrivacyOptin> privacyOptinsLoSociList = new ArrayList<>();

        UserResPrivacyDto userPrivacyObjLiguriaSoci = new UserResPrivacyDto();
        userPrivacyObjLiguriaSoci.setCoop(Constants.COOP_LIGURIA_SOCI);
        List<UserResPrivacyOptin> privacyOptinsLigSociList = new ArrayList<>();

        UserResPrivacyDto userPrivacyObjNovacoopNonSoci = new UserResPrivacyDto();
        userPrivacyObjNovacoopNonSoci.setCoop(Constants.COOP_NOVACOOP_NON_SOCI);
        List<UserResPrivacyOptin> privacyOptinsNcNonSociList = new ArrayList<>();

        UserResPrivacyDto userPrivacyObjLombardiaNonSoci = new UserResPrivacyDto();
        userPrivacyObjLombardiaNonSoci.setCoop(Constants.COOP_LOMBARDIA_NON_SOCI);
        List<UserResPrivacyOptin> privacyOptinsLoNonSociList = new ArrayList<>();

        UserResPrivacyDto userPrivacyObjLiguriaNonSoci = new UserResPrivacyDto();
        userPrivacyObjLiguriaNonSoci.setCoop(Constants.COOP_LIGURIA_NON_SOCI);
        List<UserResPrivacyOptin> privacyOptinsLigNonSociList = new ArrayList<>();

        // Dalle privacy in entrata vado a creare gli oggetti optin
        // in base al coopId le inserisco nella lista della cooperativa corrispondente
        for (UserPrivacyDto us : user.getUserPrivacyList()) {
            if (us.getCoopId().equalsIgnoreCase("1")) {
                if (us.getUserType().equals(Constants.PRIVACY_USER_TYPE_GENERICO)) {
                    privacyOptinsNcList.add(createPrivacyOptinObj(us));
                }
                if (us.getUserType().equals(Constants.PRIVACY_USER_TYPE_SOCI)) {
                    privacyOptinsNcSociList.add(createPrivacyOptinObj(us));
                }
                if (us.getUserType().equals(Constants.PRIVACY_USER_TYPE_NON_SOCI)) {
                    privacyOptinsNcNonSociList.add(createPrivacyOptinObj(us));
                }
            }
            if (us.getCoopId().equalsIgnoreCase("2")) {
                if (us.getUserType().equals(Constants.PRIVACY_USER_TYPE_GENERICO)) {
                    privacyOptinsLigList.add(createPrivacyOptinObj(us));
                }
                if (us.getUserType().equals(Constants.PRIVACY_USER_TYPE_SOCI)) {
                    privacyOptinsLigSociList.add(createPrivacyOptinObj(us));
                }
                if (us.getUserType().equals(Constants.PRIVACY_USER_TYPE_NON_SOCI)) {
                    privacyOptinsLigNonSociList.add(createPrivacyOptinObj(us));
                }
            }
            if (us.getCoopId().equalsIgnoreCase("3")) {
                if (us.getUserType().equals(Constants.PRIVACY_USER_TYPE_GENERICO)) {
                    privacyOptinsLoList.add(createPrivacyOptinObj(us));
                }
                if (us.getUserType().equals(Constants.PRIVACY_USER_TYPE_SOCI)) {
                    privacyOptinsLoSociList.add(createPrivacyOptinObj(us));
                }
                if (us.getUserType().equals(Constants.PRIVACY_USER_TYPE_NON_SOCI)) {
                    privacyOptinsLoNonSociList.add(createPrivacyOptinObj(us));
                }
            }
        }
        // setto le liste degli optin creati negli oggetti privacy delle cooperative
        userPrivacyObjNovacoop.setOptins(privacyOptinsNcList);
        userPrivacyObjLombardia.setOptins(privacyOptinsLoList);
        userPrivacyObjLiguria.setOptins(privacyOptinsLigList);
        userPrivacyObjNovacoopSoci.setOptins(privacyOptinsNcSociList);
        userPrivacyObjLombardiaSoci.setOptins(privacyOptinsLoSociList);
        userPrivacyObjLiguriaSoci.setOptins(privacyOptinsLigSociList);
        userPrivacyObjNovacoopNonSoci.setOptins(privacyOptinsNcNonSociList);
        userPrivacyObjLombardiaNonSoci.setOptins(privacyOptinsLoNonSociList);
        userPrivacyObjLiguriaNonSoci.setOptins(privacyOptinsLigNonSociList);

        // Aggiungo gli oggetti privacy delle 3 cooperative nella lista delle privacy dell'utente
        userPrivacyObjList.add(userPrivacyObjNovacoop);
        userPrivacyObjList.add(userPrivacyObjLombardia);
        userPrivacyObjList.add(userPrivacyObjLiguria);
        userPrivacyObjList.add(userPrivacyObjNovacoopSoci);
        userPrivacyObjList.add(userPrivacyObjLombardiaSoci);
        userPrivacyObjList.add(userPrivacyObjLiguriaSoci);
        userPrivacyObjList.add(userPrivacyObjNovacoopNonSoci);
        userPrivacyObjList.add(userPrivacyObjLombardiaNonSoci);
        userPrivacyObjList.add(userPrivacyObjLiguriaNonSoci);

        userRes.setUserPrivacyList(userPrivacyObjList);
        userRes.setLastPrivacyUpdate(user.getLastPrivacyUpdate());
        userRes.setUserId(user.getUserId());
        userRes.setName(user.getName());
        userRes.setSurname(user.getSurname());
        userRes.setEmail(user.getEmail());
        userRes.setEmailVerified(user.getEmailVerified());
        userRes.setCoopId(user.getCoopId());
        userRes.setRegApplication(user.getRegApplication());
        // parse data di registrazione
        String input = user.getRegDate(); // 2020 06 09 12:07:37 yyyy MM dd HH:mm:ss
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = parser.parse(input);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String formattedDate = formatter.format(date);
        userRes.setRegDate(formattedDate);
        String firstAppAccessDate = user.getFirstAppAccessDate() != null ? user.getFirstAppAccessDate() : Constants.KEYCLOAK_USER_ACCESS_DATE_EMPTY;
        userRes.setFirstAppAccessDate(firstAppAccessDate);
        String lastAppAccessDate = user.getLastAppAccessDate() != null ? user.getLastAppAccessDate() : Constants.KEYCLOAK_USER_ACCESS_DATE_EMPTY;
        userRes.setLastAppAccessDate(lastAppAccessDate);
        String userDisableApp = user.getUserDisableApp()!= null && user.getUserDisableApp().equals("true")? "SI" : "NO";
        userRes.setUserDisableApp(userDisableApp);
        String firstEcommerceAccessDate = user.getFirstEcommerceAccessDate() != null ? user.getFirstEcommerceAccessDate() : Constants.KEYCLOAK_USER_ACCESS_DATE_EMPTY;
        userRes.setFirstEcommerceAccessDate(firstEcommerceAccessDate);
        String lastEcommerceAccessDate = user.getLastEcommerceAccessDate() != null ? user.getLastEcommerceAccessDate() : Constants.KEYCLOAK_USER_ACCESS_DATE_EMPTY;
        userRes.setLastEcommerceAccessDate(lastEcommerceAccessDate);
        String userDisableEcommerce = user.getUserDisableEcommerce()!= null && user.getUserDisableEcommerce().equals("true")? "SI" : "NO";
        userRes.setUserDisableEcommerce(userDisableEcommerce);
        String firstEventiInteressiAccessDate = user.getFirstEventiInteressiAccessDate() != null ? user.getFirstEventiInteressiAccessDate() : Constants.KEYCLOAK_USER_ACCESS_DATE_EMPTY;
        userRes.setFirstEventiInteressiAccessDate(firstEventiInteressiAccessDate);
        String lastEventiInteressiAccessDate = user.getLastEventiInteressiAccessDate() != null ? user.getLastEventiInteressiAccessDate() : Constants.KEYCLOAK_USER_ACCESS_DATE_EMPTY;
        userRes.setLastEventiInteressiAccessDate(lastEventiInteressiAccessDate);
        String userDisableEventiInteressi = user.getUserDisableEventiInteressi()!= null && user.getUserDisableEventiInteressi().equals("true")? "SI" : "NO";
        userRes.setUserDisableEventiInteressi(userDisableEventiInteressi);
        String firstPortalAccessDate = user.getFirstPortalAccessDate() != null ? user.getFirstPortalAccessDate() : Constants.KEYCLOAK_USER_ACCESS_DATE_EMPTY;
        userRes.setFirstPortalAccessDate(firstPortalAccessDate);
        String lastPortalAccessDate = user.getLastPortalAccessDate() != null ? user.getLastPortalAccessDate() : Constants.KEYCLOAK_USER_ACCESS_DATE_EMPTY;
        userRes.setLastPortalAccessDate(lastPortalAccessDate);
        String userDisablePortal = user.getUserDisablePortal()!= null && user.getUserDisablePortal().equals("true")? "SI" : "NO";
        userRes.setUserDisablePortal(userDisablePortal);
        String firstPortalNovaAccessDate = user.getFirstPortalNovaAccessDate()!= null ? user.getFirstPortalNovaAccessDate() : Constants.KEYCLOAK_USER_ACCESS_DATE_EMPTY;
        userRes.setFirstPortalNovaAccessDate(firstPortalNovaAccessDate);
        String lastPortalNovaAccessDate = user.getLastPortalNovaAccessDate() != null ? user.getLastPortalNovaAccessDate() : Constants.KEYCLOAK_USER_ACCESS_DATE_EMPTY;
        userRes.setLastPortalNovaAccessDate(lastPortalNovaAccessDate);
        String userDisablePortalNova = user.getUserDisablePortalNova()!= null && user.getUserDisablePortalNova().equals("true")? "SI" : "NO";
        userRes.setUserDisablePortalNova(userDisablePortalNova);
        userRes.setStatus(user.getStatus());
        userRes.setBirthDate(user.getBirthDate());
        userRes.setGender(user.getGender());
        if (!CommonUtils.isNullOrEmpty(user.getType())) {
            userRes.setType(user.getType());
        } else {
            userRes.setType("/");
        }
        userRes.setResProvince(user.getResProvince().getName());
        userRes.setResCity(user.getResCity().getName());
        userRes.setPdvId(user.getPdvId());
        userRes.setCodicePdv(user.getCodicePdv());
        return userRes;
    }

    // creazione oggetto Privacy Optin
    public UserResPrivacyOptin createPrivacyOptinObj(UserPrivacyDto privacy) {
        UserResPrivacyOptin privacyOptinObj = new UserResPrivacyOptin();
        privacyOptinObj.setId(privacy.getId());
        privacyOptinObj.setValue(privacy.getValue());
        privacyOptinObj.setPrivacyType(privacy.getPrivacyType());
        return privacyOptinObj;
    }

    // creazione oggetto Privacy
    public UserResPrivacyDto createPrivacyObj(UserPrivacyDto privacy) {
        UserResPrivacyDto privacyObj = new UserResPrivacyDto();
        privacyObj.setCoop(privacy.getCoopId());
        UserResPrivacyOptin optinObj = new UserResPrivacyOptin();
        optinObj.setId(privacy.getVersion());
        optinObj.setValue(privacy.getValue());

        List<UserResPrivacyOptin> optinList = new ArrayList<>();
        optinList.add(optinObj);
        privacyObj.setOptins(optinList);
        return privacyObj;
    }
    
    public List<UserDto> getInactiveUsers(String application) throws ParseException {
        long startQueryTime = System.currentTimeMillis(); 
        Integer offsetMonthsInactivity = Integer.parseInt(parametriService.getParametroByName(Constants.OFFSET_MONTHS_USER_INACTIVITY).getValore());
        LocalDate dateMinusOffset = LocalDate.now().minusMonths(offsetMonthsInactivity);
        Date registrationDateTo = Date.from(dateMinusOffset.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date lastAppAccessDateTo = application.equals("all") || application.equals(Constants.APPLICATION_APPCOOP) ? Date.from(dateMinusOffset.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
        Date lastEcommerceAccessDateTo = application.equals("all") || application.equals(Constants.APPLICATION_ECOMMERCE) ? Date.from(dateMinusOffset.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
        Date lastCommunityAccessDateTo = application.equals("all") || application.equals(Constants.APPLICATION_COMMUNITY) ? Date.from(dateMinusOffset.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
        Date lastPortalAccessDateTo = application.equals("all") || application.equals(Constants.APPLICATION_PORTAL) ? Date.from(dateMinusOffset.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
        Date lastPortalNovaAccessDateTo = application.equals("all") || application.equals(Constants.APPLICATION_PORTAL_NOVA) ? Date.from(dateMinusOffset.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
        boolean lastAccessDateIncludeNulls = application.equals("all");
        logger.info("getUsersToDelete -> Inizio query");
        List<UserDto> users = userEntityDao.searchSql(
                null, 
                null, 
                null,
                null,
                registrationDateTo,
                null,
                lastAppAccessDateTo,
                lastAccessDateIncludeNulls,
                null,
                lastEcommerceAccessDateTo,
                lastAccessDateIncludeNulls,
                null,
                lastCommunityAccessDateTo,
                lastAccessDateIncludeNulls,
                null,
                lastPortalAccessDateTo,
                lastAccessDateIncludeNulls,
                null,
                lastPortalNovaAccessDateTo,
                lastAccessDateIncludeNulls,
                null,
                null,
                null);
        long stopQueryTime = System.currentTimeMillis(); 
        logger.info("getUsersToDelete -> Fine query, tempo impiegato: " + (stopQueryTime - startQueryTime)/1000 + "s");
        logger.info("getUsersToDelete -> Utenti trovati: " + (users!=null ? users.size() : "0"));
        return users;
    }
    
    public List<UserDto> getRemovedUser(String coopId, String email){
        long startQueryTime = System.currentTimeMillis(); 
        logger.info("getRemovedUser -> Inizio query");
        UserSearchResDto userSearchResDto = new UserSearchResDto();
        // chiamata a IdmCCNO
        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String backendBaseUrl = Constants.IDM_CCNO_BACKEND_BASE_URL;
        String userBaseUrl = Constants.IDM_CCNO_USER_SERVICE_BASE_URL;
        String searchUserUrl = Constants.IDM_CCNO_GET_REMOVED_USERS_SERVICE;
        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + backendBaseUrl+ "/" + userBaseUrl+ "/" + searchUserUrl;
        try {
            // chiamata POST con Content-type = application/x-www-form-urlencoded
            userSearchResDto = Unirest.get(url)
                .queryString("email", email)
                .queryString("coopId",coopId)
                .asObject(UserSearchResDto.class)
                .getBody();
        } catch (Exception ex) {
            logger.info("Errore chiamata da IdmBackend a IdmCCNO: " + url + " | parametri: coopId=" + coopId + ", email=" + email +" | errore: "+ex);
        }
        List<UserDto> users = (userSearchResDto!=null && userSearchResDto.getPayload()!=null ? userSearchResDto.getPayload().getUsers() : new ArrayList<>());
        long stopQueryTime = System.currentTimeMillis(); 
        logger.info("getRemovedUser -> Fine query, tempo impiegato: " + (stopQueryTime - startQueryTime)/1000 + "s");
        logger.info("getRemovedUser -> Utenti trovati: " + (users!=null ? users.size() : "0"));
        return users;
    }

    

}
