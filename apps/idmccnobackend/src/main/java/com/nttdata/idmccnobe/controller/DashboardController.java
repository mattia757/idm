package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController extends AbstractController {
    
//    per riabilitare la dashboard
//    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
//    public ModelAndView index() {
//        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/dashboard/dashboard");
//        modelAndView.addObject("section", "Dashboard");
//        modelAndView.addObject("subsection", "Overview");
//        return modelAndView;
//
//    }
    
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView index(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile){
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/clienti/list");
        modelAndView.addObject("section", "Clienti");
        modelAndView.addObject("pageLink","../clienti/");
        modelAndView.addObject("subsection", "Ricerca");
        modelAndView.addObject("userProfile", userProfile);
        return modelAndView;
    }
    
}
