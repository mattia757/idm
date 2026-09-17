package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.Common;
import com.nttdata.idmccnobe.dto.CreateUserBeRequest;
import com.nttdata.idmccnobe.dto.GenericDto;
import com.nttdata.idmccnobe.dto.SimpleResult;
import com.nttdata.idmccnobe.dto.UpdatePasswordReqDto;
import com.nttdata.idmccnobe.dto.UserBeRequest;
import com.nttdata.idmccnobe.dto.UserBeResDto;
import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.enumeration.ErrorCode;
import com.nttdata.idmccnobe.service.UserBeService;
import com.nttdata.idmccnobe.service.UtilsService;
import com.nttdata.idmccnobe.util.CommonUtils;
import com.nttdata.idmccnobe.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author DelorenziVa
 */

@RestController
@RequestMapping("/usersBe")
public class UserBeController extends AbstractController {
    
    @Autowired
    private UserBeService userBeService;
    
    @Autowired
    private UtilsService utilsService;

    private boolean isAdmin(UserProfile userProfile) {
        return userProfile != null && "ADMIN".equalsIgnoreCase(userProfile.getRole());
    }
    
    @RequestMapping(value={"/"}, method = RequestMethod.GET)
    public ModelAndView list(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile){
        if (!isAdmin(userProfile)) {
            return getErrorPage("Operazione non autorizzata");
        }
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/utenzeBackend/list");
        modelAndView.addObject("section", "Utenze backend");
        modelAndView.addObject("subsection", "Ricerca");
        modelAndView.addObject("userProfile", userProfile);
        return modelAndView;
    }
    
    @RequestMapping(value={"/create"}, method = RequestMethod.GET)
    public ModelAndView create(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile){
        if (!isAdmin(userProfile)) {
            return getErrorPage("Operazione non autorizzata");
        }
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/utenzeBackend/create");
        modelAndView.addObject("section", "Crea nuova utenza backend");
        modelAndView.addObject("subsection", "Crea");
        modelAndView.addObject("userProfile", userProfile);
        return modelAndView;
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public UserBeResDto search(@ModelAttribute("userBeRequest") UserBeRequest userBeRequest, @RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile){
        UserBeResDto result = new UserBeResDto();
        if (!isAdmin(userProfile)) {
            result.getHeader().setResult("KO");
            result.getHeader().setErrorMessage("Operazione non autorizzata");
            return result;
        }
        try {
            result = userBeService.searchUserBe(userBeRequest,userProfile.getRole());
            result.getPayload().setUserProfileUsername(userProfile.getUsername());
            result.getHeader().setResult("OK");
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.getHeader().setResult("KO");
            result.getHeader().setErrorMessage("Errore durante la ricerca utenze backend");
        }
        return result;
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public GenericDto createUserBe(@ModelAttribute("createUserBeRequest") CreateUserBeRequest createUserBeRequest,
                                   @RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile){
        GenericDto result = new GenericDto();
        if (!isAdmin(userProfile)) {
            result.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.GENERIC_ERROR));
            result.getHeader().setErrorMessage("Operazione non autorizzata");
            return result;
        }
        try {
            if (CommonUtils.isNullOrEmpty(createUserBeRequest.getUsername()) &&
                CommonUtils.isNullOrEmpty(createUserBeRequest.getPassword()) &&
                CommonUtils.isNullOrEmpty(createUserBeRequest.getRepeatPassword()) &&
                CommonUtils.isNullOrEmpty(createUserBeRequest.getCoop()) &&
                CommonUtils.isNullOrEmpty(createUserBeRequest.getRole()) &&
                CommonUtils.isNullOrEmpty(createUserBeRequest.getCustomerCancellation())) {
                result.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.ALL_INPUT_REQUIRED));
            } else {
                result = userBeService.createUserBe(createUserBeRequest);
            }
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.GENERIC_ERROR));
        }
        return result;
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public SimpleResult delete(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile, @RequestParam("id") String id, @RequestParam("username") String username){
        SimpleResult result = new SimpleResult();
        if (!isAdmin(userProfile)) {
            result.setCommon(new Common(Common.ACK_KO, "Operazione non autorizzata"));
            return result;
        }
        try {
            boolean response = userBeService.deleteUserBe(id);
            if (response) {
                utilsService.traceLog(userProfile.getIdCooperativa(),Constants.IDM_CCNO_BACKEND_APPLICATION,userProfile.getUsername(),logger.getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName(),"id="+id+",username="+username,"Eliminata utenza username="+username);
                result.setCommon(new Common(Common.ACK_OK));
            } else {
                result.setCommon(new Common(Common.ACK_KO, "L'utenza potrebbe essere stata rimossa dai sistemi"));
            }
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.setCommon(new Common(Common.ACK_KO, "Errore durante l'eliminazione dell'utenza backend"));
        }
        return result;
    }
    
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public GenericDto updatePassword(@ModelAttribute("updatePasswordReqDto") UpdatePasswordReqDto updatePasswordReqDto,
                                     @RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile){
        GenericDto response = new GenericDto();
        try {
            updatePasswordReqDto.setUsername(userProfile.getUsername());
            if (CommonUtils.isNullOrEmpty(updatePasswordReqDto.getCurrentPassword())) {
                response.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.FIELD_NOT_VALID_CURRENT_PASSWORD));
            } else if (CommonUtils.isNullOrEmpty(updatePasswordReqDto.getNewPassword())) {
                response.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.FIELD_NOT_VALID_NEW_PASSWORD));
            } else if ( CommonUtils.isNullOrEmpty(updatePasswordReqDto.getRepeatNewPassword())) {
                response.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.FIELD_NOT_VALID_REPEAT_NEW_PASSWORD));
            } else {
                response = userBeService.updatePassword(updatePasswordReqDto);
            }
        } catch(Exception ex) {
            response.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.GENERIC_ERROR));
        }
        return response;
    }
}
