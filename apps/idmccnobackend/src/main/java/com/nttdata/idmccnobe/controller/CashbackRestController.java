/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.Cashback;
import com.nttdata.idmccnobe.dto.CashbackFormatted;
import com.nttdata.idmccnobe.dto.DeleteCashbackRequest;
import com.nttdata.idmccnobe.dto.SaveCashbackRequest;
import com.nttdata.idmccnobe.dto.SaveCashbackResDto;
import com.nttdata.idmccnobe.dto.SearchCashbackRequest;
import com.nttdata.idmccnobe.dto.SearchCashbackResDto;
import com.nttdata.idmccnobe.dto.UpdateCashbackPdvRequest;
import com.nttdata.idmccnobe.dto.UpdateCashbackPdvResDto;
import com.nttdata.idmccnobe.dto.DeleteCashbackResDto;
import com.nttdata.idmccnobe.dto.SearchCashbackCompleteDataDto;
import com.nttdata.idmccnobe.dto.SearchCashbackCompleteDataPayloadDto;
import com.nttdata.idmccnobe.service.CashbackService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author BernardisMa
 */
@RestController
@RequestMapping(AbstractRestController.URI_REST + "/cashback")
public class CashbackRestController extends AbstractRestController {
 
    protected Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
    
    @Autowired
    private CashbackService cashbackService;
    
    @PostMapping(value="/searchCashback")
    public Object searchCashback(@ModelAttribute("searchCashbackRequest") SearchCashbackRequest searchCashbackRequest, @RequestParam(value="output", required = false) String output ){

        SearchCashbackResDto result = new SearchCashbackResDto();
        SearchCashbackCompleteDataDto resultCompleteData = new SearchCashbackCompleteDataDto();
        try {
            result = cashbackService.searchCashback(searchCashbackRequest);
            List<CashbackFormatted> listCompleteData = new ArrayList<>();
            if(result!=null && result.getHeader()!=null && result.getHeader().getResult()!=null && result.getHeader().getResult().equalsIgnoreCase("OK")){
                for(Cashback cashback : result.getPayload().getCashbackList()){
                    CashbackFormatted cashbackCompleteData = cashbackService.anagraficaCashback(Integer.toString(cashback.getId())); 
                    if(cashbackCompleteData != null){
                        listCompleteData.add(cashbackCompleteData);
                    }
                }
                resultCompleteData.setHeader(result.getHeader());
                SearchCashbackCompleteDataPayloadDto payload = new SearchCashbackCompleteDataPayloadDto(listCompleteData);
                resultCompleteData.setPayload(payload);
                return resultCompleteData;
            } else {
                return result;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.getHeader().setResult("KO");
            result.getHeader().setErrorMessage("Errore durante l'operazione cashback");
        }
        return result;
    }
    
    @PostMapping(value="/updateCashback")
    public Object updateCashback(@ModelAttribute("saveCashbackRequest") SaveCashbackRequest saveCashbackRequest, @RequestParam(value="output", required = false) String output ){

        SaveCashbackResDto result = new SaveCashbackResDto();
        try {
            result = cashbackService.updateCashback(saveCashbackRequest);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.getHeader().setResult("KO");
            result.getHeader().setErrorMessage("Errore durante l'operazione cashback");
        }
        return result;
    }
    
    @PostMapping(value="/updateCashbackPdv")
    public Object updateCashbackPdv(@ModelAttribute("updateCashbackPdvRequest") UpdateCashbackPdvRequest updateCashbackPdvRequest, @RequestParam(value="output", required = false) String output ){

        UpdateCashbackPdvResDto result = new UpdateCashbackPdvResDto();
        try {
            result = cashbackService.updateCashbackPdv(updateCashbackPdvRequest);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.getHeader().setResult("KO");
            result.getHeader().setErrorMessage("Errore durante l'operazione cashback");
        }
        return result;
    }
    
    @PostMapping(value="/delete")
    public Object deleteCashback(@RequestParam("id") Integer id, @RequestParam(value="output", required = false) String output ){
        String idString = String.valueOf(id);
        DeleteCashbackResDto result = new DeleteCashbackResDto();
        try {
            result = cashbackService.deleteCashback(idString);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.getHeader().setResult("KO");
            result.getHeader().setErrorMessage("Errore durante l'operazione cashback");
        }
        return result;
    }
    
    @PostMapping(value="/saveCashback")
    public Object saveCashback(@ModelAttribute("saveCashbackRequest") SaveCashbackRequest saveCashbackRequest, @RequestParam(value="output", required = false) String output ){

        SaveCashbackResDto result = new SaveCashbackResDto();
        try {
            result = cashbackService.saveCashback(saveCashbackRequest);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.getHeader().setResult("KO");
            result.getHeader().setErrorMessage("Errore durante l'operazione cashback");
        }
        return result;
    }
        
}
