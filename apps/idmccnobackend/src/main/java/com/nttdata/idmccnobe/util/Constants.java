package com.nttdata.idmccnobe.util;

/**
 *
 * @author smartinenghi
 */
public class Constants {
    
    public static final String IDM_CCNO_BACKEND_APPLICATION = "IdmCCNOBackend";
    
    public static final String KEYCLOAK_REALM_ADMIN_USERNAME = "KEYCLOAK_REALM_ADMIN_USERNAME";
    
    public static final String USER_PROFILE = "userProfile";
    public static final String SESSION_USER_PROFILE = "sessionUserProfile";
    public static final String SESSION_CSRF_TOKEN = "csrfToken";
    public static final String CSRF_HEADER_NAME = "X-CSRF-Token";
    public static final String CSRF_PARAMETER_NAME = "csrfToken";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String SUCCESS_MESSAGE = "successMessage";
    public static final String IDM_BE_JWT_SECRET_PROPERTY = "idm.be.jwt.secret";
    public static final String IDM_BE_JWT_SECRET_ENV = "IDM_BE_JWT_SECRET";
            
    public static final String COOP_LOMBARDIA = "Coop Lombardia";
    public static final String COOP_NOVACOOP = "Novacoop";
    public static final String COOP_LIGURIA = "Coop Liguria";
    public static final String COOP_LOMBARDIA_SOCI = "Coop Lombardia Soci";
    public static final String COOP_NOVACOOP_SOCI = "Novacoop Soci";
    public static final String COOP_LIGURIA_SOCI = "Coop Liguria Soci";
    public static final String COOP_LOMBARDIA_NON_SOCI = "Coop Lombardia Non Soci";
    public static final String COOP_NOVACOOP_NON_SOCI = "Novacoop Non Soci";
    public static final String COOP_LIGURIA_NON_SOCI = "Coop Liguria Non Soci";
    public static final String APPLICATION_ECOMMERCE = "ecommerce";
    public static final String APPLICATION_APPCOOP = "appcoop";    
    public static final String APPLICATION_COMMUNITY = "community";  
    public static final String APPLICATION_PORTAL = "portal";  
    public static final String APPLICATION_PORTAL_NOVA = "portal-nova";  
    public static final String DEVICE_WEB = "web";
    
    
    public static final String USER_STATUS_ACTIVE = "Attivo";
    public static final String USER_STATUS_INACTIVE = "Non attivo";
    public static final String USER_GENDER_FEMALE = "Femminile";
    public static final String USER_GENDER_MALE = "Maschile";
    public static final String USER_GENDER_I = "Indefinito";
    public static final String USER_TYPE_F = "Persona fisica";
    public static final String USER_TYPE_G = "Giuridica";
    public static final String USER_ROLE_ADMIN = "Amministratore";
    public static final String USER_ROLE_CCNO = "CCNO";
    public static final String USER_ROLE_MODIFY = "Editore";
    public static final String USER_ROLE_VIEW = "Lettore";

    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    public static final String CONTENT_TYPE_JSON = "application/json";
    
    public static final String IDM_BE_TOKEN_VALIDITY_MINUTES = "IDM_BE_TOKEN_VALIDITY_MINUTES";
    
    public static final String IDM_CCNO_HOST = "IDM_CCNO_HOST";
    public static final String APIM_ENDPOINT = "APIM_ENDPOINT";
    public static final String COUPON_IMAGE_APIM_AUTH_KEY = "COUPON_IMAGE_APIM_AUTH_KEY";
    public static final String COUPON_IMAGE_GCP_BASE_URL = "COUPON_IMAGE_GCP_BASE_URL";
    public static final String COUPON_IMAGE_APIM_PATH = "/IdmCCNO/v1/saveImage";
    public static final String COUPON_IMAGE_FOLDER = "Coupon";
    public static final String COUPON_IMAGE_PUBLIC_PATH = "/CCNO/Coupon";
    public static String PATH_STORELOCATOR_PDVCODEDETAILS="PATH_STORELOCATOR_PDVCODEDETAILS";
    public static final String IDM_CCNO_BASE_URL = "IdmCCNO";
    public static final String IDM_CCNO_REST_BASE_URL = "rest";
    public static final String IDM_CCNO_ECOMMERCE_BASE_URL = "ecommerce";
    public static final String IDM_CCNO_BACKEND_BASE_URL = "backend";
    public static final String IDM_CCNO_PRIVACIES_BASE_URL = "/api/privacies";
    public static final String IDM_CCNO_GET_PRIVACY_DISCLAIMERS_BASE_URL = "getPrivacyDisclaimers";
    public static final String IDM_CCNO_LOYALTY_SERVICE_BASE_URL = "loyalty";
    public static final String IDM_CCNO_UTILS_SERVICE_BASE_URL = "utils";
    public static final String IDM_CCNO_USER_SERVICE_BASE_URL = "user";
    public static final String IDM_CCNO_VERIFY_LOYALTY_SERVICE = "verifyLoyaltyInfo";
    public static final String IDM_CCNO_REMOVE_CARD_SERVICE = "removeCard";
    public static final String IDM_CCNO_ADD_CARD_SERVICE = "addCard";
    public static final String IDM_CCNO_SEND_RESET_PASSWORD_CODE_SERVICE = "sendResetPasswordCode";
    public static final String IDM_CCNO_SEND_EMAIL_ADDRESS_VERIFICATION_CODE_SERVICE = "resendEmailAddressVerificationCode";
    public static final String IDM_CCNO_SEARCH_USER_SERVICE = "search";
    public static final String IDM_CCNO_REMOVE_USER_SERVICE = "removeUser";
    public static final String IDM_CCNO_TRACE_LOG_SERVICE = "traceLog";
    public static final String IDM_CCNO_GET_REMOVED_USERS_SERVICE = "getRemovedUsers";
    
    public static final String IDM_CCNO_CASHBACK_SERVICE_BASE_URL = "cashback";
    public static final String IDM_CCNO_CASHBACK_ANAGRAFICA_SERVICE_BASE_URL = "anagrafica";
    public static final String IDM_CCNO_CASHBACK_PRODUCT_LINES = "findProductLines";
    public static final String IDM_CCNO_CASHBACK_ACCUMULATION = "Accumulation";
    public static final String IDM_CCNO_CASHBACK_FRUITION = "Fruition";
    public static final String IDM_CCNO_CASHBACK_FIND_ACC_FRU_LINES = "findAccFruProductLines";
    public static final String APIM_CCNO_CASHBACK_GET_PDV_LIST_BY_COOP_IDS = "getPdvListByCoopIds";
    public static final String APIM_STORELOCATORE_URL = "servizicoop/storelocator/services/v1";
    public static final String IDM_CCNO_CASHBACK_FIND_PDV_ID_SELECTED = "findPdvIdSelected";
    public static final String IDM_CCNO_CASHBACK_SAVE = "saveCashback";
    public static final String IDM_CCNO_CASHBACK_UPDATE = "updateCashback";
    public static final String IDM_CCNO_CASHBACK_UPDATE_PDV = "updateCashbackPdv";
    public static final String IDM_CCNO_CASHBACK_DELETE = "deleteCashback";
    
    public static final String EXCEL_OUTPUT_FORMAT = "excel";
    public static final String EXCEL_DATA_KEY = "EXCEL_DATA_KEY";
    public static final String EXCEL_FILENAME_KEY = "EXCEL_FILENAME_KEY";
    
    public static final String KEYCLOAK_USER_ATTRIBUTE_COOPID = "coopId";
    public static final String KEYCLOAK_USER_ATTRIBUTE_FIDELITY_CARDS = "fidelityCards";
    public static final String KEYCLOAK_USER_ATTRIBUTE_REG_APPLICATION = "application";
    public static final String KEYCLOAK_USER_ATTRIBUTE_BIRTHDATE = "birthdate";
    public static final String KEYCLOAK_USER_ATTRIBUTE_GENDER = "gender";
    public static final String KEYCLOAK_USER_ATTRIBUTE_TYPE = "type";
    public static final String KEYCLOAK_USER_ATTRIBUTE_PROVINCE = "residenceProvince";
    public static final String KEYCLOAK_USER_ATTRIBUTE_CITY = "residenceCity";
    public static final String KEYCLOAK_USER_ATTRIBUTE_PRIVACY = "privacy";
    public static final String KEYCLOAK_USER_ATTRIBUTE_FIRST_APP_ACCESS_DATE = "firstAppAccessDate";
    public static final String KEYCLOAK_USER_ATTRIBUTE_LAST_APP_ACCESS_DATE = "lastAppAccessDate";
    public static final String KEYCLOAK_USER_ATTRIBUTE_FIRST_ECOMMERCE_ACCESS_DATE = "firstEcommerceAccessDate";
    public static final String KEYCLOAK_USER_ATTRIBUTE_LAST_ECOMMERCE_ACCESS_DATE = "lastEcommerceAccessDate";
    public static final String KEYCLOAK_USER_ATTRIBUTE_FIRST_EVENTI_INTERESSI_ACCESS_DATE = "firstEventiInteressiAccessDate";
    public static final String KEYCLOAK_USER_ATTRIBUTE_LAST_EVENTI_INTERESSI_ACCESS_DATE = "lastEventiInteressiAccessDate";
    public static final String KEYCLOAK_USER_ATTRIBUTE_FIRST_PORTAL_ACCESS_DATE = "firstPortalAccessDate";
    public static final String KEYCLOAK_USER_ATTRIBUTE_LAST_PORTAL_ACCESS_DATE = "lastPortalAccessDate";
    public static final String KEYCLOAK_USER_ATTRIBUTE_FIRST_PORTAL_NOVA_ACCESS_DATE = "firstPortalNovaAccessDate";
    public static final String KEYCLOAK_USER_ATTRIBUTE_LAST_PORTAL_NOVA_ACCESS_DATE = "lastPortalNovaAccessDate";
    public static final String KEYCLOAK_USER_ATTRIBUTE_LAST_MODIFY_DATE = "lastModifyDate";
    public static final String KEYCLOAK_USER_ATTRIBUTE_DELETE_APP = "userDisableApp";
    public static final String KEYCLOAK_USER_ATTRIBUTE_DELETE_ECOMMERCE = "userDisableEcommerce";
    public static final String KEYCLOAK_USER_ATTRIBUTE_USER_DISABLE_EVENTI_INTERESSI = "userDisableEventiInteressi";
    public static final String KEYCLOAK_USER_ATTRIBUTE_DELETE_PORTAL = "userDisablePortal";
    public static final String KEYCLOAK_USER_ATTRIBUTE_DELETE_PORTAL_NOVA = "userDisablePortalNova";
    public static final String KEYCLOAK_USER_ATTRIBUTE_USER_REACTIVE_CODE = "userReactiveCode";
    public static final String KEYCLOAK_USER_ATTRIBUTE_USER_REACTIVE_CODE_EXPIRATION_TIME = "userReactiveCodeExpirationTime";
    public static final String KEYCLOAK_USER_ATTRIBUTE_PDV_ID = "pdvId";
    public static final String KEYCLOAK_USER_ATTRIBUTE_LAST_PRIVACY_UPDATE = "lastPrivacyUpdate";
    
    public static final String KEYCLOAK_USER_ACCESS_DATE_EMPTY = "Nessun accesso effettuato";
    
    public static final String PRIVACY_USER_TYPE_NON_SOCI = "0";
    public static final String PRIVACY_USER_TYPE_SOCI = "1";
    public static final String PRIVACY_USER_TYPE_GENERICO = "2";
    
    public static final String ESTRAZIONE_DATI_EXCEL = "estrazioneDatiExcel";
    public static final String APPLICATION_TYPE_APP = "appcoop";
    
    public static final String EMAIL_VERIFIED = "True";   
    
    public static final String APPLICATION_CODE = "ICOOP";
    public static final String INSTANCE_CODE = "ICOOP_";
    public static final int MESSAGE_BADGE = 1;
    public static final int MESSAGE_LIFE = 1440;
    public static final String MESSAGE_SOUND = "default";
    public static final String MESSAGE_BODY_PARAM_KEY_TEXT = "text";
    public static final String MESSAGE_BODY_PARAM_TYPE_POPUP = "popup";
    public static final String MESSAGE_BODY_PARAM_KEY_IMAGE = "image";
    public static final String MESSAGE_BODY_PARAM_KEY_LINK = "link";
    public static final String MESSAGE_BODY_PARAM_TYPE_BANNER = "banner_v1";
 
//    Notifiche Push
    public static final String NOTIFICHE_PUSH_AUTH_USERNAME="NOTIFICHE_PUSH_BASIC_AUTH_USERNAME";
    public static final String NOTIFICHE_PUSH_AUTH_PASSWORD="NOTIFICHE_PUSH_BASIC_AUTH_PASSWORD";
    public static final String NOTIFICHE_PUSH_LIMIT_USERS = "NOTIFICHE_PUSH_LIMIT_USERS";
    public static final String NOTIFICHE_PUSH_SERVIZIO_INVIO_NOTIFICHE ="NOTIFICHE_PUSH_SERVIZIO_INVIO_NOTIFICHE";
    public static final String AZURE_APIM_SERVIZICOOP_URL = "AZURE_APIM_SERVIZICOOP_URL";
   
//    Proxy 
    public static final String PROXY_HOST = "PROXY_HOST";
    public static final String PROXY_PORT = "PROXY_PORT";
    
    public static final String OFFSET_MONTHS_USER_INACTIVITY = "OFFSET_MONTHS_USER_INACTIVITY";
    
// Couponing
    public static final String COUPONING_SECTION = "Couponing";
    public static final String COUPONING_SUBSECTION = "Ricerca";
    public static final String COUPONING_ATTIVO = "ATTIVO";
    public static final String COUPONING_INATTIVO = "INATTIVO";
    
}
