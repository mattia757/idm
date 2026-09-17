package com.nttdata.idmccnobe.service;


import com.nttdata.idmccnobe.dto.AddCardResDto;
import com.nttdata.idmccnobe.dto.RemoveCardResDto;
import kong.unirest.Unirest;
import com.nttdata.idmccnobe.dto.VerifyLoyaltyResDto;
import com.nttdata.idmccnobe.util.Constants;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DelorenziVa
 */
@Service("LoyaltyService")
@Transactional
public class LoyaltyService extends AbstractService {
    
    @Autowired
    private ParameterBeService parameterBeService;
    
    public VerifyLoyaltyResDto verifyLoyaltyInfo(String eanCard, String birthDate, String coopId) throws ParseException {
        VerifyLoyaltyResDto verifyLoyaltyResDto = new VerifyLoyaltyResDto();
        
        // chiamata a IdmCCNO

        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String ecommerceBaseUrl = Constants.IDM_CCNO_ECOMMERCE_BASE_URL;
        String loyaltyBaseUrl = Constants.IDM_CCNO_LOYALTY_SERVICE_BASE_URL;
        String verifyLoyaltyInfoUrl = Constants.IDM_CCNO_VERIFY_LOYALTY_SERVICE;
     
        // parametri
        String application = Constants.APPLICATION_ECOMMERCE;

        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + ecommerceBaseUrl+ "/" + loyaltyBaseUrl + "/" + verifyLoyaltyInfoUrl;
        
//        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url + " | parametri: " + eanCard + ", " + birthDate + ", " + application + ", " + coopId);
                
        // chiamata POST con Content-type = application/x-www-form-urlencoded
        verifyLoyaltyResDto = Unirest.post(url)
            .field("eanCard", eanCard)
            .field("birthDate", birthDate)
            .field("application", application)
            .field("coopId", coopId)
            .asObject(VerifyLoyaltyResDto.class)
            .getBody();
        
        return verifyLoyaltyResDto;
    }
    
    public RemoveCardResDto removeCard(String userId, String eanCard, String birthDate, String coopId) {
        RemoveCardResDto removeCardResDto = new RemoveCardResDto();
        
        // chiamata a IdmCCNO

        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String ecommerceBaseUrl = Constants.IDM_CCNO_ECOMMERCE_BASE_URL;
        String loyaltyBaseUrl = Constants.IDM_CCNO_LOYALTY_SERVICE_BASE_URL;
        String removeCard = Constants.IDM_CCNO_REMOVE_CARD_SERVICE;
     
        // parametri
        String application = Constants.APPLICATION_ECOMMERCE;

        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + ecommerceBaseUrl+ "/" + loyaltyBaseUrl + "/" + removeCard;
        
//        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url + " | parametri: " + eanCard + ", " + birthDate + ", " + application + ", " + coopId);
                
        // chiamata POST con Content-type = application/x-www-form-urlencoded
        removeCardResDto = Unirest.post(url)
            .field("userId", userId)
            .field("eanCard", eanCard)
            .field("birthDate", birthDate)
            .field("application", application)
            .field("coopId", coopId)
            .asObject(RemoveCardResDto.class)
            .getBody();
        
        return removeCardResDto;
    }
    
    
    public AddCardResDto addCard(String userId, String eanCard, String birthDate, String coopId) {
        AddCardResDto addCardResDto = new AddCardResDto();
        
        // chiamata a IdmCCNO

        String idmCcnoHost = parameterBeService.getParameterValueByName(Constants.IDM_CCNO_HOST);
        String idmCcnoBaseUrl = Constants.IDM_CCNO_BASE_URL;
        String rest = Constants.IDM_CCNO_REST_BASE_URL;
        String ecommerceBaseUrl = Constants.IDM_CCNO_ECOMMERCE_BASE_URL;
        String loyaltyBaseUrl = Constants.IDM_CCNO_LOYALTY_SERVICE_BASE_URL;
        String addCard = Constants.IDM_CCNO_ADD_CARD_SERVICE;
     
        // parametri
//        String userId = addCardReqDto.getUserId();
//        String eanCard = addCardReqDto.getEanCard();
//        String birthDate = addCardReqDto.getBirthDate();
        String application = Constants.APPLICATION_ECOMMERCE;
//        String coopId = addCardReqDto.getCoopId();
        
        String url = idmCcnoHost + "/" + idmCcnoBaseUrl + "/" + rest + "/" + ecommerceBaseUrl+ "/" + loyaltyBaseUrl + "/" + addCard;
        
//        logger.info("Chiamata da IdmBackend a IdmCCNO: " + url + " | parametri: " + eanCard + ", " + birthDate + ", " + application + ", " + coopId);
                
        // chiamata POST con Content-type = application/x-www-form-urlencoded
        addCardResDto = Unirest.post(url)
            .field("userId", userId)
            .field("eanCard", eanCard)
            .field("birthDate", birthDate)
            .field("application", application)
            .field("coopId", coopId)
            .asObject(AddCardResDto.class)
            .getBody();
        
        return addCardResDto;
    }
}
