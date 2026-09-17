package com.nttdata.idmccnobe.controller;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractController {
    protected Logger logger = Logger.getLogger(this.getClass());
    
    protected ModelAndView getErrorPage(String errorMessage) {
        ModelAndView errorPage = new ModelAndView("/jsp/template/template", "page", "/jsp/common/errorPage");
        
        errorPage.addObject("section", "Dashboard");
        errorPage.addObject("subsection", "Errore");
        
        errorPage.addObject("errorMessage", errorMessage);
        
        return errorPage;
    }
}
