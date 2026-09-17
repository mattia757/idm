package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.dto.UserResDto;
import com.nttdata.idmccnobe.service.UserService;
import com.nttdata.idmccnobe.util.Constants;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author DelorenziVa
 */
@Controller
@RequestMapping("/clienti")
public class UserController extends AbstractController{
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value={"/"}, method = RequestMethod.GET)
    public ModelAndView search(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile){
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/clienti/list");
        modelAndView.addObject("section", "Clienti");
        modelAndView.addObject("subsection", "Ricerca");
        modelAndView.addObject("userProfile", userProfile);
        return modelAndView;
    }
    
    @GetMapping("/view")
    public ModelAndView view(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile, @RequestParam("userId") String userId) throws ParseException {
        return getViewPage(userProfile, userId);
    }
    
    public ModelAndView getViewPage(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile, String id) throws ParseException {
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/clienti/view");
        modelAndView.addObject("section", "Clienti");
        modelAndView.addObject("pageLink","../clienti/");
        modelAndView.addObject("subsection", "Dettaglio");
        
        UserResDto userResDto = userService.getUser(userProfile, id);
        if (userResDto == null) {
            modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/clienti/list");
            modelAndView.addObject("section", "Clienti");
            modelAndView.addObject("subsection", "Ricerca");
            modelAndView.addObject("userProfile", userProfile);
            modelAndView.addObject(Constants.ERROR_MESSAGE, "Utente non trovato");
            return modelAndView;
        }
        
        modelAndView.addObject("item", userResDto);
        
        return modelAndView;
    }
}
