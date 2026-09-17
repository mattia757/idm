package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.service.UtilsService;
import com.nttdata.idmccnobe.util.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author DelorenziVa
 */
@Controller
@RequestMapping("/logout")
public class LogoutController extends AbstractController  {
    
    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    public ModelAndView auth(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ModelAndView("/jsp/template/templateLogin", "page", "/jsp/template/login"); 
    }
}
