package com.nttdata.idmccnobe.service;

import com.nttdata.idmccnobe.dto.GenericDto;
import com.nttdata.idmccnobe.util.Constants;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author DelorenziVa
 */
@Service("UtilsService")
@Transactional
public class UtilsService extends AbstractService {
    
    @Autowired
    private ParameterBeService parameterBeService;
    
    @Autowired
    private ParametriService parametriService;
    
    
    
    public GenericDto sendResetPasswordCode(String email, String coopId) throws UnsupportedEncodingException{
        GenericDto genericDto = new GenericDto();
        
        // chiamata a IdmCCNO

        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String ecommerceBaseUrl = Constants.IDM_CCNO_ECOMMERCE_BASE_URL;
        String utilsBaseUrl = Constants.IDM_CCNO_UTILS_SERVICE_BASE_URL;
        String sendResetPasswordCodeUrl = Constants.IDM_CCNO_SEND_RESET_PASSWORD_CODE_SERVICE;
     
        // parametri
        String application = Constants.APPLICATION_ECOMMERCE;
        String device = Constants.DEVICE_WEB;
               
        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + ecommerceBaseUrl+ "/" + utilsBaseUrl + "/" + sendResetPasswordCodeUrl;
        
//        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url + " | parametri: " + email + ", " + coopId + ", " + application + ", " + device);
                
        // chiamata POST con Content-type = application/x-www-form-urlencoded
        genericDto = Unirest.post(url)
            .field("email", email)
            .field("coopId", coopId)
            .field("application", application)
            .field("device", device)
            .asObject(GenericDto.class)
            .getBody();
        return genericDto;
    }
    
    
    public GenericDto sendEmailAddressVerificationCode(String email, String coopId) throws UnsupportedEncodingException{
        GenericDto genericDto = new GenericDto();
        
        // chiamata a IdmCCNO

        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String ecommerceBaseUrl = Constants.IDM_CCNO_ECOMMERCE_BASE_URL;
        String utilsBaseUrl = Constants.IDM_CCNO_UTILS_SERVICE_BASE_URL;
        String sendEmailAddressVerificationCodeUrl = Constants.IDM_CCNO_SEND_EMAIL_ADDRESS_VERIFICATION_CODE_SERVICE;
     
        // parametri
        String application = Constants.APPLICATION_ECOMMERCE;
        String device = Constants.DEVICE_WEB;

        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + ecommerceBaseUrl+ "/" + utilsBaseUrl + "/" + sendEmailAddressVerificationCodeUrl;
        
//        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url + " | parametri: " + email + ", " + coopId + ", " + application + ", " + device);
                
        // chiamata POST con Content-type = application/x-www-form-urlencoded
        genericDto = Unirest.post(url)
            .field("email", email)
            .field("coopId", coopId)
            .field("application", application)
            .field("device", device)
            .asObject(GenericDto.class)
            .getBody();
        return genericDto;
    }
    
    // chiamata a servizio IdmCCNO
    public String traceLog(Integer cooperativa, String application, String user, String action, String parameters, String description){
        String response = null;
        try {
            // chiamata a IdmCCNO
            String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
            String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
            String rest = Constants.IDM_CCNO_REST_BASE_URL;
            String backendBaseUrl = Constants.IDM_CCNO_BACKEND_BASE_URL;
            String utilsBaseUrl = Constants.IDM_CCNO_UTILS_SERVICE_BASE_URL;
            String traceLogServiceUrl = Constants.IDM_CCNO_TRACE_LOG_SERVICE;
            String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + backendBaseUrl+ "/" + utilsBaseUrl + "/" + traceLogServiceUrl;
//            logger.info("Chiamata da IdmBackend a IdmCCNO: " + url + " | parametri: cooperativa="+cooperativa+", application="+application+", user="+user+", action="+action+", parameters="+parameters+", description="+description);
            response = Unirest.post(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("cooperativa", cooperativa)
                .field("application", application)
                .field("user", user)
                .field("action", action)
                .field("parameters", (parameters!=null ? parameters : ""))
                .field("description", (description!=null ? description : ""))
                .asObject(String.class)
                .getBody();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return response;
    }
    
    public RestTemplate proxyRestTemplate() {
    String proxyHost = parametriService.getParametroByName(Constants.PROXY_HOST).getValore();
    String proxyPortStr = parametriService.getParametroByName(Constants.PROXY_PORT).getValore();

    if (proxyHost != null && !proxyHost.trim().isEmpty()
            && proxyPortStr != null && !proxyPortStr.trim().isEmpty()) {
        try {
            int proxyPort = Integer.parseInt(proxyPortStr);
            if (proxyPort > 0) {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
                SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
                requestFactory.setProxy(proxy);
                return new RestTemplate(requestFactory);
            }
        } catch (NumberFormatException e) {
            logger.error("proxyRestTemplate porta non valida: "+e.getMessage());
        }
    }

    // Se i dati non sono validi, RestTemplate senza proxy
    return new RestTemplate();
}
    
}
