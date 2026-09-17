package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.LoginDto;
import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.interceptor.CsrfInterceptor;
import com.nttdata.idmccnobe.model.UserBe;
import com.nttdata.idmccnobe.service.LoginService;
import com.nttdata.idmccnobe.service.UtilsService;
import com.nttdata.idmccnobe.util.CommonUtils;
import com.nttdata.idmccnobe.util.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController extends AbstractController {

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private UtilsService utilsService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("/jsp/template/templateLogin", "page", "/jsp/template/login");
    }

    @RequestMapping(value = {"auth"}, method = RequestMethod.POST)
    public ModelAndView auth(@ModelAttribute("userLoginDto") LoginDto loginDto, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        
        try {
            if (CommonUtils.isNullOrEmpty(loginDto.getUsername())) {
                mav = getLoginPage();
                mav.addObject(Constants.ERROR_MESSAGE, "Campo Username non valido");
            } else if (CommonUtils.isNullOrEmpty(loginDto.getPassword())) {
                mav = getLoginPage();
                mav.addObject(Constants.ERROR_MESSAGE, "Campo Password non valido");
            } else {
                UserBe user = loginService.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
                if (user != null) {
                    Integer cooperativa = user.getCooperativa().getId();
                    String username = user.getUsername();
                    UserProfile userProfile = new UserProfile();
                    userProfile.setIdCooperativa(cooperativa);
                    userProfile.setNomeCooperativa(user.getCooperativa().getRagioneSociale());
                    userProfile.setRole(user.getRole());
                    userProfile.setUsername(user.getUsername());
                    userProfile.setCustomerCancellation(String.valueOf(user.getCustomerDeletionEnabled()));

                    HttpSession existingSession = request.getSession(false);
                    if (existingSession != null) {
                        existingSession.invalidate();
                    }
                    HttpSession session = request.getSession(true);
                    session.setAttribute(Constants.SESSION_USER_PROFILE, userProfile);
                    session.setAttribute(Constants.SESSION_CSRF_TOKEN, CsrfInterceptor.createToken());
                    request.setAttribute(Constants.USER_PROFILE, userProfile);
                    utilsService.traceLog(cooperativa,Constants.IDM_CCNO_BACKEND_APPLICATION,username,logger.getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName(),null,null);

                    mav = getUserPage(userProfile);
                } else {
                    mav = getLoginPage();
                    mav.addObject(Constants.ERROR_MESSAGE, "Utente non trovato");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            mav = getLoginPage();
            mav.addObject(Constants.ERROR_MESSAGE, "Si è verificato un errore durante l'accesso");
        }
        return mav;
    }
     
    public ModelAndView getLoginPage() {
        return new ModelAndView("/jsp/template/templateLogin", "page", "/jsp/template/login"); 
    }
    
    public ModelAndView getDashboardPage() {
        return new ModelAndView("/jsp/template/template", "page", "/jsp/dashboard/dashboard"); 
    }
    
    public ModelAndView getUserPage(UserProfile userProfile){
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/clienti/list");
        modelAndView.addObject("section", "Clienti");
        modelAndView.addObject("pageLink","../clienti/");
        modelAndView.addObject("subsection", "Ricerca");
        modelAndView.addObject("userProfile", userProfile);
        return modelAndView;
    }
}
