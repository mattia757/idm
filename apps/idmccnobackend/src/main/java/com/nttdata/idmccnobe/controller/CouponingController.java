/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.CouponingRedPromoTypeDto;
import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.dto.VoucherDto;
import com.nttdata.idmccnobe.service.CouponingService;
import com.nttdata.idmccnobe.util.Constants;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author EFERRASXG
 */

@Controller
@RequestMapping("/couponing")
public class CouponingController extends AbstractRestController {
    
    @Autowired
    private CouponingService couponingService;
    
    
    @GetMapping("/ricerca")
    public ModelAndView loadPageForCouponingSearch(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile) {
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/couponing/list");
        modelAndView.addObject("section", Constants.COUPONING_SECTION);
        modelAndView.addObject("subsection", Constants.COUPONING_SUBSECTION);
        modelAndView.addObject("userProfile", userProfile);
        return modelAndView;
    }
    
    @GetMapping("/edit")
    public ModelAndView edit(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile, @RequestParam("voucherId") String voucherId) throws IOException, ParseException {
        return getEditPage(voucherId);
    }
    
    public ModelAndView getEditPage(String voucherId) throws IOException, ParseException {
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/couponing/edit");
        modelAndView.addObject("section", "Anagrafica Coupon");
        modelAndView.addObject("subsection", "Dettagli");
        modelAndView.addObject("pageLink","../couponing/ricerca/");
        List<CouponingRedPromoTypeDto> promoType = couponingService.findAllRedPromoType();
        VoucherDto voucher = couponingService.getVoucherById(voucherId);
        modelAndView.addObject("promoTypes",promoType );
        modelAndView.addObject("voucher",voucher );      
                
        return modelAndView;
    } 
    
}
