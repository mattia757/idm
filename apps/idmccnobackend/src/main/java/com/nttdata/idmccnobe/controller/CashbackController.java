/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.idmccnobe.dto.CashbackFormatted;
import com.nttdata.idmccnobe.dto.ProductLineDto;
import com.nttdata.idmccnobe.dto.SearchCashbackRequest;
import com.nttdata.idmccnobe.dto.SearchCashbackResDto;
import com.nttdata.idmccnobe.dto.SelectedPdvForDropdown;
import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.enumeration.ListaTabLinkCashback;
import com.nttdata.idmccnobe.service.CashbackService;
import com.nttdata.idmccnobe.service.UserService;
import com.nttdata.idmccnobe.util.Constants;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author BernardisMa
 */
@Controller
@RequestMapping("/cashback")
public class CashbackController extends AbstractRestController {
    
    @Autowired
    private CashbackService cashbackService;
    
    @GetMapping("/ricerca")
    public ModelAndView loadPageForCashbackSearch(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile) {
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/cashback/list");
        modelAndView.addObject("section", "Cashback");
        modelAndView.addObject("subsection", "Ricerca");
        modelAndView.addObject("userProfile", userProfile);
        return modelAndView;
    }   
    
    @GetMapping("/edit")
    public ModelAndView edit(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile, @RequestParam("id") Integer id) throws IOException, ParseException {
        return getEditPage(id);
    }
    
    public ModelAndView getEditPage(Integer id) throws IOException, ParseException {
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/cashback/edit");
        modelAndView.addObject("section", "Anagrafica Cashback");
        modelAndView.addObject("subsection", "Dettagli");
        modelAndView.addObject("pageLink","../cashback/ricerca/");
        modelAndView.addObject("tabSelected", ListaTabLinkCashback.CASHBACK_VIEW_DETTAGLIO.tabCashback());
        List<ProductLineDto> productLines = cashbackService.findProductLines();
        CashbackFormatted cashbackToShow = cashbackService.anagraficaCashback(id.toString());
        modelAndView.addObject("cashback", cashbackToShow);
        modelAndView.addObject("statoCashback", cashbackService.getStatus(cashbackToShow));
        // Accumulation
        List<ProductLineDto> accumulationLines = cashbackService.findAccFruProductLinesResponse(id, Constants.IDM_CCNO_CASHBACK_ACCUMULATION);
        Map<String, Integer> accumulationMap = cashbackService.createSelectionMap(productLines, accumulationLines);
        modelAndView.addObject("accumulationLines", accumulationMap);
        // Fruitiion
        List<ProductLineDto> fruitionLines = cashbackService.findAccFruProductLinesResponse(id, Constants.IDM_CCNO_CASHBACK_FRUITION);
        Map<String, Integer> fruitionMap = cashbackService.createSelectionMap(productLines, fruitionLines);
        modelAndView.addObject("fruitionLines", fruitionMap);
        
        return modelAndView;
    } 
    
    @GetMapping("/editPdv")
    public ModelAndView editPdv(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile, @RequestParam("id") Integer id) throws IOException {
        return getEditPdvPage(id);
    }
    
    public ModelAndView getEditPdvPage(Integer id) throws IOException {
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/pdvCashback/editPdv");
        modelAndView.addObject("section", "Anagrafica Cashback");
        modelAndView.addObject("subsection", "PDV Associati");
        modelAndView.addObject("pageLink","../cashback/ricerca/");
        modelAndView.addObject("tabSelected", ListaTabLinkCashback.CASHBACK_TAB_PDV.tabCashback());
        modelAndView.addObject("cashback", cashbackService.anagraficaCashback(id.toString()));
        
        // Novacoop
        List<String> listSelectedForNovacoop = cashbackService.findPdvIdSelected("1", id);
        List<SelectedPdvForDropdown> listPdvNovacoop = cashbackService.retrievePdvForCoopId(1);
        if (listSelectedForNovacoop != null && !listSelectedForNovacoop.isEmpty() 
                && listSelectedForNovacoop.get(0) != null && listSelectedForNovacoop.get(0).equalsIgnoreCase("0")){
            modelAndView.addObject("novacooopAllSelected", "1");
            modelAndView.addObject("novacooopPdvIdSelected", listPdvNovacoop);
        } else {
            modelAndView.addObject("novacooopAllSelected", "0");
            modelAndView.addObject("novacooopPdvIdSelected", cashbackService.updateSelectedStatus(listPdvNovacoop,listSelectedForNovacoop));
        }
        
        // Liguria
        List<String> listSelectedForLiguria = cashbackService.findPdvIdSelected("2", id);
        List<SelectedPdvForDropdown> listPdvLiguria = cashbackService.retrievePdvForCoopId(2);
        if (listSelectedForLiguria != null && !listSelectedForLiguria.isEmpty() 
                && listSelectedForLiguria.get(0) != null && listSelectedForLiguria.get(0).equalsIgnoreCase("0")){
            modelAndView.addObject("liguriaAllSelected", "1");
            modelAndView.addObject("liguriaPdvIdSelected", listPdvLiguria);
        } else {
            modelAndView.addObject("liguriaAllSelected", "0");
            modelAndView.addObject("liguriaPdvIdSelected", cashbackService.updateSelectedStatus(listPdvLiguria,listSelectedForLiguria));
        }
        
        // Lombardia
        List<String> listSelectedForLombardia = cashbackService.findPdvIdSelected("3", id);
        List<SelectedPdvForDropdown> listPdvLombardia = cashbackService.retrievePdvForCoopId(3);
        if (listSelectedForLombardia != null && !listSelectedForLombardia.isEmpty() 
                && listSelectedForLombardia.get(0) != null && listSelectedForLombardia.get(0).equalsIgnoreCase("0")){
            modelAndView.addObject("lombardiaAllSelected", "1");
            modelAndView.addObject("lombardiaPdvIdSelected", listPdvLombardia);
        } else {
            modelAndView.addObject("lombardiaAllSelected", "0");
            modelAndView.addObject("lombardiaPdvIdSelected", cashbackService.updateSelectedStatus(listPdvLombardia,listSelectedForLombardia));
        }
        
        return modelAndView;
    }
    
    @GetMapping("/crea")
    public ModelAndView create(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile) throws IOException {
        return getCreatePage();
    }
    
    public ModelAndView getCreatePage() throws IOException {
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/cashback/create");
        modelAndView.addObject("section", "Cashback");
        modelAndView.addObject("subsection", "Dettagli");
        modelAndView.addObject("pageLink","../cashback/ricerca/");
        List<ProductLineDto> productLines = cashbackService.findProductLines();
        // Accumulation
        Map<String, Integer> accumulationMap = cashbackService.createSelectionMap(productLines, null);
        modelAndView.addObject("accumulationLines", accumulationMap);
        // Fruitiion
        Map<String, Integer> fruitionMap = cashbackService.createSelectionMap(productLines, null);
        modelAndView.addObject("fruitionLines", fruitionMap);
        
        return modelAndView;
    } 
    
}
