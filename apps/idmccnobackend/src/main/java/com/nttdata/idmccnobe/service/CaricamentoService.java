package com.nttdata.idmccnobe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.idmccnobe.dto.CcnoPrivacyOptinDto;
import com.nttdata.idmccnobe.dto.NotificaPushExcelDto;
import com.nttdata.idmccnobe.dto.PrivaciesDto;
import com.nttdata.idmccnobe.dto.PrivacyRequestPayload;
import com.nttdata.idmccnobe.dto.UserDto;
import com.nttdata.idmccnobe.dto.UserEanCardDto;
import com.nttdata.idmccnobe.dto.UserPrivacyDto;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.dto.UserRequest;
import com.nttdata.idmccnobe.dto.UserSearchResDto;
import com.nttdata.idmccnobe.dto.pushNotification.ApplicationDto;
import com.nttdata.idmccnobe.dto.pushNotification.FilterDto;
import com.nttdata.idmccnobe.dto.pushNotification.MessageBodyDto;
import com.nttdata.idmccnobe.dto.pushNotification.MessageBodyParamDto;
import com.nttdata.idmccnobe.dto.pushNotification.MessageDto;
import com.nttdata.idmccnobe.dto.pushNotification.PushNotificationRequestPayloadDto;
import com.nttdata.idmccnobe.dto.pushNotification.PushNotificationResponse;
import com.nttdata.idmccnobe.model.Uri;
import com.nttdata.idmccnobe.util.Constants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import kong.unirest.Unirest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author FerrandicoSi
 */

@Service("CaricamentoService")
@Transactional
public class CaricamentoService extends AbstractService {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ParameterBeService parameterBeService;
    
    @Autowired
    private UriService uriService;

    @Autowired
    private ParametriService parametriService;
    
    @Autowired
    private UtilsService utilsService;
    
    public Map<String, Object> importNotifichePushExcel(UserProfile userProfile, MultipartFile uplFileNotificaPush) {
        Map<String, Object> returnObj = new HashMap<>();
        List<NotificaPushExcelDto> notificheList = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(uplFileNotificaPush.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (isEmptyRow(row)) {
                    continue; // riga con valori nulli o vuoti: skip
                }
                Cell cell = getCellByIndex(row, 0);
                try {
                    switch (cell.getCellType()) {
                        case STRING: {
                            //se hanno lasciato la riga di intestazione, la salto
                            if (cell.getStringCellValue().trim().equalsIgnoreCase("COOP_ID")) {
//                                logger.info("riga di intestazione....continuo");
                                continue;
                            }
                        }
                    }
                    NotificaPushExcelDto notificaPushDto = new NotificaPushExcelDto();

                    notificaPushDto.setCoopId(converXlsField(row.getCell(0)));
                    notificaPushDto.setEmail(converXlsField(row.getCell(1)));
                    notificaPushDto.setTesto(converXlsField(row.getCell(2)));
                    notificaPushDto.setMessaggio(converXlsField(row.getCell(3)));
                    notificaPushDto.setImage(converXlsField(row.getCell(4)));
                    notificaPushDto.setLink(converXlsField(row.getCell(5)));
                    notificaPushDto.setDeepLink(converXlsField(row.getCell(6)));
                    notificaPushDto.setProfilazione(converXlsField(row.getCell(7)));
                    notificaPushDto.setMarketing(converXlsField(row.getCell(8)));
                    notificheList.add(notificaPushDto);

                } catch (Exception e) {
                    logger.error("errore parsing excel", e);
                    returnObj.put(Constants.ERROR_MESSAGE, "Attenzione! si e' verificato un errore processando la riga " + (row.getRowNum() + 1) + " Causa: " + e.getLocalizedMessage() + ". Tutte le righe precedenti sono state elaborate correttamente.");
                    break;
                }
            }
        } catch (Throwable e) {
            logger.error("errore importazione excel", e);
            returnObj.put(Constants.ERROR_MESSAGE, "Attenzione! si è verificato un errore generico nell'importazione del file excel");
        }
        returnObj.put(Constants.ESTRAZIONE_DATI_EXCEL, notificheList);
        return returnObj;
    }
    
    private boolean isEmptyRow(Row row) {
        Iterator<Cell> cellIterator = row.iterator();
        boolean result = true;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (cell != null && !StringUtils.isEmpty(converXlsField(cell))) {
                result = false;
                break;
            }
        }
        return result;
    }
    
    private String converXlsField(Cell cellValue) {
        String cellValueString = "";
        if (cellValue != null) {
            switch (cellValue.getCellType()) {
                case STRING:
                    cellValueString = cellValue.getStringCellValue().trim();
                    break;

                case NUMERIC:
                    cellValueString = String.valueOf((int) cellValue.getNumericCellValue());
                    break;
            }
        }
        return cellValueString;
    }
    
    public Cell getCellByIndex(Row row, int index) {
        Cell result = row.getCell(index);
        if (result == null) {
            return row.createCell(index);
        } else {
            return result;
        }
    }
    
    /*
    private String parseAndconverXlsField(Cell cellValue) {
        String cellValueString = "";
        String returnValue = "";
        if (cellValue!=null) {
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    cellValueString = cellValue.getStringCellValue().trim();
                    break;

                case Cell.CELL_TYPE_NUMERIC:
                    cellValueString = String.valueOf((int) cellValue.getNumericCellValue());
                    break;
            }
            String[] codeAndText = cellValueString.split("-");
            if (!StringUtils.isEmpty(codeAndText[0])) {
                returnValue = codeAndText[0];
            }
        }
        return returnValue;
    }
    */
    
    public Map<Integer,List<UserDto>> getAllUsers(){
       Map<Integer,List<UserDto>> returnObj = new HashMap(); 
        for(int i=1;i<4;i++){            
            UserRequest request = new UserRequest();
            request.setCoopId(String.valueOf(i));
            UserSearchResDto response = userService.searchUser(request);
            if(response!=null && response.getHeader()!=null && response.getHeader().getResult()!=null && response.getHeader().getResult().equalsIgnoreCase("OK")){
                returnObj.put(i, response.getPayload().getUsers());
            }
        }
        return returnObj;
    }
    
      private PrivaciesDto getPrivacyDisclaimers(PrivacyRequestPayload requestBody){
        PrivaciesDto res = new PrivaciesDto();
        // chiamata a IdmCCNO
        
        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String privaciesBaseUrl = Constants.IDM_CCNO_PRIVACIES_BASE_URL;
        
        String getPrivacyDisclaimerUrl = Constants.IDM_CCNO_GET_PRIVACY_DISCLAIMERS_BASE_URL;

        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest  + privaciesBaseUrl+ "/" + getPrivacyDisclaimerUrl;
        
//        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url + " | parametri: " + requestBody.getCoopId() + ", " + requestBody.getUserType() + ", " + requestBody.getApplication());
                
        // chiamata POST con Content-type = application/x-www-form-urlencoded
        res = Unirest.post(url)
            .header("Content-Type", "application/json")
            .body(requestBody)
            .asObject(PrivaciesDto.class)
            .getBody();

        return res;
    }
      
    public Map<Integer, Map<Integer, PrivaciesDto>> getAllPrivacyDisclaimers() {
        Map<Integer, Map<Integer, PrivaciesDto>> returnObj = new HashMap<>();               
        for (int i = 1; i < 4; i++) { 
            Map<Integer, PrivaciesDto> intermediateObj = new HashMap<>();
            for (int userType = 0; userType < 2; userType++) {
                //carico per ogni cooperativa solo gli optin socio e non socio, escludo i generici                
                PrivacyRequestPayload request = new PrivacyRequestPayload();
                request.setCoopId(String.valueOf(i));
                request.setApplication(Constants.APPLICATION_TYPE_APP);
                request.setUserType(String.valueOf(userType));
                PrivaciesDto response = this.getPrivacyDisclaimers(request);
                if (response.getHeader().getResult().equalsIgnoreCase("OK")) {
                    intermediateObj.put(userType, response);
                }
            }
            returnObj.put(i, intermediateObj);
        }
        return returnObj;
    }
    
    public List<NotificaPushExcelDto> estrazioneUtentiValidi(List<NotificaPushExcelDto> users) {
        List<NotificaPushExcelDto> res = new ArrayList<>();
        
//        Map<Integer, List<UserDto>> listaUtentiCompleti = this.getAllUsers();
//        List<UserDto> utentiDiCooperativa = new ArrayList<>();
        
        Map<Integer, Map<Integer, PrivaciesDto>> elencoPrivacy = this.getAllPrivacyDisclaimers();
        for (NotificaPushExcelDto actual : users) {
            String coopId = actual.getCoopId();
            if (Integer.valueOf(coopId) == 1 || Integer.valueOf(coopId) == 2 || Integer.valueOf(coopId) == 3) {
                UserRequest userRequest = new UserRequest();
//                userRequest.setCoopId(coopId);
                userRequest.setEmail(actual.getEmail());
                UserSearchResDto userSearchRes = userService.searchUser(userRequest);
            
                if(userSearchRes != null && userSearchRes.getPayload() != null
                        && userSearchRes.getPayload().getUsers() != null && userSearchRes.getPayload().getUsers().get(0) != null){
                
//                    utentiDiCooperativa = listaUtentiCompleti.get(Integer.valueOf(coopId));
//                    //cerco negli utenti di cooperativa la mail della riga excel processata
//                    UserDto utenteDB = utentiDiCooperativa.stream()
//                            .filter(utente -> actual.getEmail().equalsIgnoreCase(utente.getEmail()))
//                            .findAny()
//                            .orElse(null);
//                    if (utenteDB != null) {
                        String emailVerified = userSearchRes.getPayload().getUsers().get(0).getEmailVerified();
                        if (emailVerified.equalsIgnoreCase(Constants.EMAIL_VERIFIED)) {
                            if (this.checkPrivacyEspresse(elencoPrivacy, userSearchRes.getPayload().getUsers().get(0), actual)) {
                                res.add(actual);
                                logger.info("CaricamentoService-estrazioneUtentiValidi:utenteValido= "+actual.getEmail()+" -coopId= "+actual.getCoopId()+" -profilazione= "+actual.getProfilazione()+" -marketing= "+actual.getMarketing());
                            } else {
                                logger.info("CaricamentoService-estrazioneUtentiValidi:utente con con privacy non coerenti rispetto all'excel= "+actual.getEmail()+" -coopId= "+actual.getCoopId());
                            }
                        } else {
                            logger.info("CaricamentoService-estrazioneUtentiValidi:utente con mail non verificata= "+actual.getEmail()+" -coopId= "+actual.getCoopId());
                        }
//                    }
                } else {
                    logger.info("CaricamentoService-estrazioneUtentiValidi:utente non trovato= "+actual.getEmail()+" -coopId= "+actual.getCoopId());
                }
            } else {
                logger.info("CaricamentoService-estrazioneUtentiValidi:utenteNonValido per cooperativa errata= "+actual.getEmail()+" -coopId= "+actual.getCoopId());
            }

        }
        return res;
    }

    private boolean checkPrivacyEspresse(Map<Integer, Map<Integer, PrivaciesDto>> elencoPrivacy, UserDto utente, NotificaPushExcelDto utenteExcel) {
        boolean res = false;
        if (utenteExcel.getProfilazione().equalsIgnoreCase("NO") && utenteExcel.getMarketing().equalsIgnoreCase("NO")) {
            return true;
        } else {
            Boolean profilazione = utenteExcel.getProfilazione().equalsIgnoreCase("SI");
            Boolean marketing = utenteExcel.getMarketing().equalsIgnoreCase("SI");
            Integer coopId = Integer.valueOf(utenteExcel.getCoopId());
            Map<Integer, PrivaciesDto> privacyCooperativa = elencoPrivacy.get(coopId);
            //controllo che l'utente sia socio o meno, il coopId della tessera deve essere lo stesso dell'utente excel
            Integer userType = isSocio(utente);
            List<CcnoPrivacyOptinDto> optinUtentePerTipo = privacyCooperativa.get(userType).getPayload().getOptinPrivacy();
            List<UserPrivacyDto> privacyEspresse = utente.getUserPrivacyList();
            if (profilazione) {
//                logger.info("controllo profilazione");
                for (UserPrivacyDto actual : privacyEspresse) {
//                    logger.info("controllo privacy utente: "+actual);
                    if (actual.getUserType().equalsIgnoreCase(String.valueOf(userType)) && actual.getPrivacyType() == 1) {
                        String idActual = actual.getId();
//                        logger.info("controllo privacy utente idActual: "+idActual);
                        for (CcnoPrivacyOptinDto actualOptin : optinUtentePerTipo) {
//                            logger.info("controllo optinUtentePerTipo: "+actualOptin);
                            if (idActual.equalsIgnoreCase(actualOptin.getId()) && actualOptin.getPrivacyType().equalsIgnoreCase("1")) {
                                //la privacy non espressa è = null                                
                                res = actual.getValue() != null && actual.getValue();
//                                logger.info("risultato : "+res);
                            }
                        }
                    }
                }
                //se la codnizione è fallita, noncontrollo nemmeno quella successiva
                if(!res){
                    return res;
                }
            }
            if (marketing) {
                logger.info("controllo marketing");
                for (UserPrivacyDto actual : privacyEspresse) {
                    if (actual.getUserType().equalsIgnoreCase(String.valueOf(userType)) && actual.getPrivacyType() == 2) {
                        String idActual = actual.getId();
                        for (CcnoPrivacyOptinDto actualOptin : optinUtentePerTipo) {
                            if (idActual.equalsIgnoreCase(actualOptin.getId()) && actualOptin.getPrivacyType().equalsIgnoreCase("2")) {
                                res = actual.getValue() != null && actual.getValue();
                            }
                        }
                    }
                }
            }

            return res;
        }
    }
    
    private Integer isSocio(UserDto utente){
        Integer res = 0;
        String coopId = utente.getCoopId();
        if(utente.getEanCards()!=null){
            for(UserEanCardDto actual : utente.getEanCards()){
                if(actual.getCoopId().equalsIgnoreCase(coopId)){
                    return 1;
                }
            }
        }
        return res;
    }
    
    public String generazioneNotifichePush (List<NotificaPushExcelDto> utentiNotificabili){
        String result = "";
        int pushUsersLimit = Integer.parseInt(parametriService.getParametroByName(Constants.NOTIFICHE_PUSH_LIMIT_USERS).getValore());
        logger.info("Limte utenti per singola chiamata a notifiche push: "+pushUsersLimit);
        if (!utentiNotificabili.get(0).getCoopId().isEmpty() || !utentiNotificabili.get(0).getTesto().isEmpty()
                || !utentiNotificabili.get(0).getMessaggio().isEmpty() 
                || (!utentiNotificabili.get(0).getImage().isEmpty() && !utentiNotificabili.get(0).getLink().isEmpty()) 
                || !utentiNotificabili.get(0).getDeepLink().isEmpty()){
            try{
                PushNotificationRequestPayloadDto pushNotificationRequestPayloadDto = new PushNotificationRequestPayloadDto();
                MessageDto message = new MessageDto();

                //--- MESSAGE BODY ---
                MessageBodyDto messageBody = new MessageBodyDto();
                List<MessageBodyParamDto> listMessageBodyParams = new ArrayList<>();

                if (!utentiNotificabili.get(0).getMessaggio().isEmpty()){
                    //--- TIPO POPUP ---
                    listMessageBodyParams.add( new MessageBodyParamDto(Constants.MESSAGE_BODY_PARAM_KEY_TEXT, utentiNotificabili.get(0).getMessaggio()) );
                    messageBody.setType(Constants.MESSAGE_BODY_PARAM_TYPE_POPUP);
                } else if (!utentiNotificabili.get(0).getImage().isEmpty() && !utentiNotificabili.get(0).getLink().isEmpty()) {
                    //--- TIPO BANNER_V1 ---
                    listMessageBodyParams.add( new MessageBodyParamDto(Constants.MESSAGE_BODY_PARAM_KEY_IMAGE, utentiNotificabili.get(0).getImage()) );
                    listMessageBodyParams.add( new MessageBodyParamDto(Constants.MESSAGE_BODY_PARAM_KEY_LINK, utentiNotificabili.get(0).getLink()) );
                    messageBody.setType(Constants.MESSAGE_BODY_PARAM_TYPE_BANNER);
                } else if (!utentiNotificabili.get(0).getDeepLink().isEmpty()){
                    //--- TIPO POPUP (DEEPLINK) ---
                    String deepLink = utentiNotificabili.get(0).getDeepLink().trim();
                    if (deepLink != null && deepLink.length() >= 2){
                        if(deepLink.charAt(0) != '{' ){
                            deepLink = "{" + deepLink;
                        }
                        if (deepLink.charAt(deepLink.length() - 1) != '}'){
                            deepLink = deepLink + "}";
                        }
                        listMessageBodyParams.add( new MessageBodyParamDto(Constants.MESSAGE_BODY_PARAM_KEY_TEXT, deepLink) );
                        messageBody.setType(Constants.MESSAGE_BODY_PARAM_TYPE_POPUP);
                    }
                }

                if (!listMessageBodyParams.isEmpty()){
                    messageBody.setParams(listMessageBodyParams);
                    
                    //--- MESSAGE ---
                    message.setBody(messageBody); 
                    message.setBadge(Constants.MESSAGE_BADGE);
                    message.setHidden(false);
                    message.setLife(Constants.MESSAGE_LIFE);
                    message.setSound(Constants.MESSAGE_SOUND);
                    message.setText(utentiNotificabili.get(0).getTesto());

                    //--- APPLICATION ---
                    ApplicationDto application = new ApplicationDto();
                    application.setCode(Constants.APPLICATION_CODE);

                    //--- FILTER ---
                    FilterDto filter = new FilterDto();
                    filter.setInstance(Constants.INSTANCE_CODE + utentiNotificabili.get(0).getCoopId());
                    filter.setDmIds(null);
                    filter.setTechnologyClasses(null);
                    filter.setAppVersionLike(null);

                    //--- FINAL REQUEST OBJECT ---
                    pushNotificationRequestPayloadDto.setNoFeedback(true);
                    pushNotificationRequestPayloadDto.setSimulate(false); //Mettere true per test
                    pushNotificationRequestPayloadDto.setRemoteTag(null);
                    pushNotificationRequestPayloadDto.setApplication(application);
                    pushNotificationRequestPayloadDto.setFilter(filter);
                    pushNotificationRequestPayloadDto.setMessage(message);

                    // Invio la richiesta all'API a blocchi di x utenti (definiti in "UsersLimit"), se un bloco di invio fallisce passo al successivo
                    int count = 0;
                    List<String> listUsernames = new ArrayList<>();
                    for  (NotificaPushExcelDto actual : utentiNotificabili) {
                        count = count + 1;
                        String username = md5Encryption(actual.getEmail().toLowerCase());
                        if (username != null){
                            listUsernames.add(username);
                            if(count%pushUsersLimit == 0){
                                // Se ho raggiunto un multiplo di UsersLimit allora invio il blocco (es. UsersLimit=500 -> 500,1000,1500,..)
                                pushNotificationRequestPayloadDto.getFilter().setUsernames(listUsernames);
                                // Chiamata a callNotificationApi
                                logger.info("Chiamata callNotificationApi: blocco= " + (count-pushUsersLimit)+"-"+count + ", utenti=" + listUsernames.size());
                                PushNotificationResponse response = this.callNotificationApi(pushNotificationRequestPayloadDto);
                                if (response!=null && response.getStatus()!=null && response.getStatus().getReturnCode()!=null && response.getStatus().getReturnCode()==0) {
                                    logger.info("Chiamata a callNotificationApi eseguita con successo!!!");
                                    result = "OK";
                                    listUsernames = new ArrayList<>();
                                } else {
                                    result = "Errore nella chiamata a callNotificationApi";
                                    logger.error(result + " blocco= " + (count-pushUsersLimit)+"-"+count + ", utenti=" + listUsernames.size() + " - errore: " +(response!=null && response.getStatus()!=null ? response.getStatus().toString() : "response o status non valorizzate"));
                                    listUsernames = new ArrayList<>();
                                }
                            }
                        }
                    }
                    // Se ho utenti residui in coda, li invio (es. 1537 utenti -> in blocchi da 500 ne restano 37)
                    if (!listUsernames.isEmpty()) {
                        pushNotificationRequestPayloadDto.getFilter().setUsernames(listUsernames);
                        // Chiamata a callNotificationApi
                        logger.info("Chiamata a callNotificationApi: blocco= " + (count-(count%pushUsersLimit))+"-"+count + ", utenti=" + listUsernames.size());
                        PushNotificationResponse response = this.callNotificationApi(pushNotificationRequestPayloadDto);
                        if (response!=null && response.getStatus()!=null && response.getStatus().getReturnCode()!=null && response.getStatus().getReturnCode()==0) {
                            logger.info("Chiamata a callNotificationApi eseguita con successo!!!");
                            result = "OK";
                        } else {
                            result = "Errore nella chiamata a generazioneNotifichePush";
                            logger.error(result + " blocco= " + (count-(count%pushUsersLimit))+"-"+count + ", utenti=" + listUsernames.size() + " - errore: " +(response!=null && response.getStatus()!=null ? response.getStatus().toString() : "response o status non valorizzate"));
                        }
                    }
                } else {
                    result = "Errore nella creazione del messaggio delle notifiche push. Controllare il file caricato.";
                }
            } catch (Throwable e){
                logger.error("Errore nella chiamata generazioneNotifichePush: ", e);
                result = "Errore nella chiamata generazioneNotifichePush";
            }
        } else {
            result = "Errore nella compilazione dei campi del file Excel";
        }
        return result;
    }
    
    private PushNotificationResponse callNotificationApi(PushNotificationRequestPayloadDto requestBody) throws JsonProcessingException{
        PushNotificationResponse res = new PushNotificationResponse();
        
        Uri ccnoUri = uriService.getUriByName(Constants.AZURE_APIM_SERVIZICOOP_URL);
        if(ccnoUri != null){
            
            // Chiamata a callNotificationApi
            String apimHost = ccnoUri.getValore();
            String pushNotificationService = uriService.getUriByName(Constants.NOTIFICHE_PUSH_SERVIZIO_INVIO_NOTIFICHE).getValore();
            String username = uriService.getUriByName(Constants.NOTIFICHE_PUSH_AUTH_USERNAME).getValore();
            String password = uriService.getUriByName(Constants.NOTIFICHE_PUSH_AUTH_PASSWORD).getValore();
            String pushCreds = username+":"+password;
            byte[] pushCredsBytes = pushCreds.getBytes();
            byte[] base64PushBytes = Base64.encodeBase64(pushCredsBytes);
            String base64Creds = new String(base64PushBytes);
           
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            RestTemplate restTemplate = utilsService.proxyRestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Basic " + base64Creds);
            HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(requestBody), headers);
            
            try {
                final String serviceToCall = apimHost + pushNotificationService;
                logger.info("Chiamata a servizio NotifichePush APIM -> " + serviceToCall);
                ResponseEntity<String> response = restTemplate.exchange(serviceToCall, HttpMethod.POST, entity, String.class);
                String responseBody = response.getBody();
                res = mapper.readValue(responseBody, PushNotificationResponse.class);
            } catch(Exception e) {
                    logger.error("CaricamentoService-callNotificationApi - "+e.getMessage());
            }
        }
        return res;
        
    }
    
    private String md5Encryption(String str) {
        String result = "";
        try {
            // Create an instance of MessageDigest with MD5 algorithm
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Convert the string to bytes and add to the message digest
            md.update(str.getBytes());

            // Generate the MD5 hash
            byte[] mdBytes = md.digest();

            // Convert the hash bytes to a hexadecimal representation
            StringBuilder sb = new StringBuilder();
            for (byte mdByte : mdBytes) {
                sb.append(Integer.toHexString((mdByte & 0xFF) | 0x100).substring(1, 3));
            }

            result = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            logger.error("Errore nella conversione della stringa " + str + " in md5: ", e);
        }
        return result;
    }
        
}
