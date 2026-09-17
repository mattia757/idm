package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.service.PartecipazioneInteresseService;
import com.nttdata.idmccnobe.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/partecipazioni")
public class PartecipazioneInteresseController extends AbstractRestController {

    @Autowired
    private PartecipazioneInteresseService partecipazioneInteresseService;

    @GetMapping("/ricerca")
    public ModelAndView loadPageForPartecipazioniSearch(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile) {
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/partecipazioni/list");
        modelAndView.addObject("section", "Partecipazioni");
        modelAndView.addObject("subsection", "Ricerca");
        modelAndView.addObject("userProfile", userProfile);
        modelAndView.addObject("tipologieEventoList", partecipazioneInteresseService.findAllTipologieEvento());
        return modelAndView;
    }
}
