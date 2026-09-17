package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.CommonDto;
import com.nttdata.idmccnobe.dto.Common;
import com.nttdata.idmccnobe.dto.PartecipazioneInteresseDto;
import com.nttdata.idmccnobe.dto.PartecipazioneInteresseSearchRequest;
import com.nttdata.idmccnobe.dto.SearchPartecipazioniPayloadDto;
import com.nttdata.idmccnobe.dto.SearchPartecipazioniResDto;
import com.nttdata.idmccnobe.dto.SimpleResult;
import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.service.PartecipazioneInteresseService;
import com.nttdata.idmccnobe.util.Constants;
import com.nttdata.idmccnobe.view.ExcelView;
import java.util.List;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(AbstractRestController.URI_REST + "/partecipazioni")
public class PartecipazioneInteresseRestController extends AbstractRestController {

    protected Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private PartecipazioneInteresseService partecipazioneInteresseService;

    @PostMapping(value = "/searchPartecipazioni")
    public SearchPartecipazioniResDto searchPartecipazioni(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile,
            @ModelAttribute("partecipazioneInteresseSearchRequest") PartecipazioneInteresseSearchRequest request) {
        SearchPartecipazioniResDto res = new SearchPartecipazioniResDto();
        try {
            List<PartecipazioneInteresseDto> partecipazioni = partecipazioneInteresseService.searchPartecipazioni(request, userProfile);
            SearchPartecipazioniPayloadDto payload = new SearchPartecipazioniPayloadDto();
            payload.setPartecipazioniList(partecipazioni);
            res.getHeader().setResult(CommonDto.ACK_OK);
            res.setPayload(payload);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            res.getHeader().setResult(CommonDto.ACK_KO);
            res.getHeader().setErrorCode(17);
            res.getHeader().setErrorMessage("Errore durante la ricerca partecipazioni");
        }
        return res;
    }

    @PostMapping(value = "/exportPartecipazioni")
    public ModelAndView exportPartecipazioni(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile,
            @ModelAttribute("partecipazioneInteresseSearchRequest") PartecipazioneInteresseSearchRequest request,
            HttpServletResponse response, HttpServletRequest httpRequest) {
        ModelAndView modelAndView;
        try {
            List<PartecipazioneInteresseDto> partecipazioni = partecipazioneInteresseService.searchPartecipazioni(request, userProfile);
            modelAndView = new ModelAndView(new ExcelView(), Constants.EXCEL_DATA_KEY, partecipazioni);
            modelAndView.addObject(Constants.EXCEL_FILENAME_KEY, "Estrazione-partecipazioni");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/partecipazioni/list");
            modelAndView.addObject("section", "Partecipazioni");
            modelAndView.addObject("subsection", "Ricerca");
            modelAndView.addObject("userProfile", userProfile);
            modelAndView.addObject("tipologieEventoList", partecipazioneInteresseService.findAllTipologieEvento());
            modelAndView.addObject(Constants.ERROR_MESSAGE, "Errore durante la generazione del file excel");
        }
        addExportCompletedCookie(response, httpRequest);
        return modelAndView;
    }

    @PostMapping(value = "/deletePartecipazione")
    public SimpleResult deletePartecipazione(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile,
            @RequestParam("id") Long id) {
        SimpleResult result = new SimpleResult();
        try {
            partecipazioneInteresseService.deletePartecipazione(id);
            result.setCommon(new Common(Common.ACK_OK));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.setCommon(new Common(Common.ACK_KO, "Errore durante l'eliminazione della partecipazione"));
        }
        return result;
    }

    private void addExportCompletedCookie(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("ExportCompleted", "1");
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
    }
}
