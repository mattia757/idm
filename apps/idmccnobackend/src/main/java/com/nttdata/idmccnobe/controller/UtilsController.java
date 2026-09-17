package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.GenericDto;
import com.nttdata.idmccnobe.service.UtilsService;
import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DelorenziVa
 */
@RestController
@RequestMapping(AbstractRestController.URI_REST + "/utils")
public class UtilsController extends AbstractController {
    
    @Autowired
    private UtilsService utilsService;
    
    @PostMapping(value="/sendResetPasswordCode")
    public GenericDto sendResetPasswordCode(@RequestParam("email") String email,
                                            @RequestParam("coopId") String coopId) throws UnsupportedEncodingException{
        GenericDto genericDto = new GenericDto();
        genericDto = utilsService.sendResetPasswordCode(email, coopId);
        return genericDto;
    }
    
    @PostMapping(value="/sendEmailAddressVerificationCode")
    public GenericDto sendEmailAddressVerificationCode(@RequestParam("email") String email,
                                            @RequestParam("coopId") String coopId) throws UnsupportedEncodingException{
        GenericDto genericDto = new GenericDto();
        genericDto = utilsService.sendEmailAddressVerificationCode(email, coopId);
        return genericDto;
    }
}
