package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.AddCardResDto;
import com.nttdata.idmccnobe.dto.RemoveCardResDto;
import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.dto.VerifyLoyaltyResDto;
import com.nttdata.idmccnobe.service.LoyaltyService;
import com.nttdata.idmccnobe.util.Constants;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 *
 * @author DelorenziVa
 */

@RestController
@RequestMapping(AbstractRestController.URI_REST + "/loyalty")
public class LoyaltyRestController extends AbstractController {
    
    @Autowired
    private LoyaltyService loyaltyService;
    
    @PostMapping(value="/verifyLoyaltyInfo")
    public VerifyLoyaltyResDto verifyLoyaltyInfo(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile, @RequestParam("eanCard") String eanCard, @RequestParam("birthDate") String birthDate, @RequestParam("coopId") String coopId) throws ParseException {
        VerifyLoyaltyResDto verifyLoyaltyResDto = new VerifyLoyaltyResDto();
        if (!canAccessCooperativa(userProfile, coopId)) {
            setUnauthorizedResult(verifyLoyaltyResDto.getHeader());
            return verifyLoyaltyResDto;
        }
        verifyLoyaltyResDto = loyaltyService.verifyLoyaltyInfo(eanCard, birthDate, coopId);
        return verifyLoyaltyResDto;
    }
    
    
    @PostMapping(value="/addCard")
    public AddCardResDto addCard(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile, @RequestParam("userId") String userId, @RequestParam ("eanCard") String eanCard, @RequestParam("birthDate") String birthDate, @RequestParam("coopId") String coopId){
        AddCardResDto addCardResDto = new AddCardResDto();
        if (!canAccessCooperativa(userProfile, coopId)) {
            setUnauthorizedResult(addCardResDto.getHeader());
            return addCardResDto;
        }
        addCardResDto = loyaltyService.addCard(userId, eanCard, birthDate, coopId);
        return addCardResDto;
    }
    
    @PostMapping(value="/removeCard")
    public RemoveCardResDto removeCard(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile, @RequestParam("userId") String userId, @RequestParam("eanCard") String eanCard, @RequestParam("birthDate") String birthDate, @RequestParam("coopId") String coopId) {
        RemoveCardResDto removeCardResDto = new RemoveCardResDto();
        if (!canAccessCooperativa(userProfile, coopId)) {
            setUnauthorizedResult(removeCardResDto.getHeader());
            return removeCardResDto;
        }
        removeCardResDto = loyaltyService.removeCard(userId, eanCard, birthDate, coopId);
        return removeCardResDto;
    }

    private boolean canAccessCooperativa(UserProfile userProfile, String coopId) {
        if (userProfile == null || userProfile.getRole() == null || coopId == null) {
            return false;
        }
        if ("ADMIN".equalsIgnoreCase(userProfile.getRole()) || "CCNO".equalsIgnoreCase(userProfile.getRole())) {
            return true;
        }
        return userProfile.getIdCooperativa() != null
                && String.valueOf(userProfile.getIdCooperativa()).equals(coopId.trim());
    }

    private void setUnauthorizedResult(com.nttdata.idmccnobe.dto.CommonDto header) {
        header.setResult("KO");
        header.setErrorMessage("Operazione non autorizzata per la cooperativa selezionata");
    }
}
