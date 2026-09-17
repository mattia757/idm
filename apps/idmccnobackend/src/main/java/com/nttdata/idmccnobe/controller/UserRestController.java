package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.Common;
import com.nttdata.idmccnobe.dto.CommonDto;
import com.nttdata.idmccnobe.dto.RemoveUserDto;
import com.nttdata.idmccnobe.dto.SimpleResult;
import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.dto.UserRequest;
import com.nttdata.idmccnobe.dto.UserSearchResDto;
import com.nttdata.idmccnobe.service.UserService;
import com.nttdata.idmccnobe.service.UtilsService;
import com.nttdata.idmccnobe.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DelorenziVa
 */
@RestController
@RequestMapping(AbstractRestController.URI_REST + "/clienti")
public class UserRestController extends AbstractRestController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UtilsService utilsService;
    
    @PostMapping(value="/searchUser")
    public Object searchUser(@ModelAttribute("userRequest") UserRequest userRequest, @RequestParam(value="output", required = false) String output ){

        UserSearchResDto result = new UserSearchResDto();
        try {
            result = userService.searchUser(userRequest);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.getHeader().setResult("KO");
            result.getHeader().setErrorMessage("Errore durante la ricerca utenti");
        }
        return result;
    }
    
    @PostMapping(value = "/removeUser")
    public SimpleResult removeUser(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile, @RequestParam("userId") String userId, @RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("surname") String surname){
        SimpleResult result = new SimpleResult();
        try {
            String deletionUser = userProfile.getUsername();
            RemoveUserDto removeUserDto = userService.removeUser(userId,deletionUser);
            if (removeUserDto!=null && removeUserDto.getHeader()!=null && removeUserDto.getHeader().getResult()!=null && removeUserDto.getHeader().getResult().equals(CommonDto.ACK_OK)) {
                utilsService.traceLog(userProfile.getIdCooperativa(),Constants.IDM_CCNO_BACKEND_APPLICATION,userProfile.getUsername(),logger.getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName(),"userId="+userId,"Eliminata utenza userId="+userId+", email="+email+", name="+name+", surname="+surname);
                result.setCommon(new Common(Common.ACK_OK));
            } else {
                String serviceErrorMessage = (removeUserDto!=null && removeUserDto.getHeader()!=null && removeUserDto.getHeader().getErrorMessage()!=null ? ": "+removeUserDto.getHeader().getErrorMessage() : "");
                result.setCommon(new Common(Common.ACK_KO, "Errore durante l'eliminazione dell'utenza"+serviceErrorMessage));
            }
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.setCommon(new Common(Common.ACK_KO, "Errore durante l'eliminazione dell'utenza"));
        }
        return result;
    }
    
}
