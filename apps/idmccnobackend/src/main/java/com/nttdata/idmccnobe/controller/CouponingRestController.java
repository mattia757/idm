/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.CouponingSearchRequestDto;
import com.nttdata.idmccnobe.dto.DeleteCouponResDto;
import com.nttdata.idmccnobe.dto.ModifyVoucherDto;
import com.nttdata.idmccnobe.dto.SearchCouponPayloadDto;
import com.nttdata.idmccnobe.dto.SearchCouponRequest;
import com.nttdata.idmccnobe.dto.SearchCouponResDto;
import com.nttdata.idmccnobe.dto.VoucherDto;
import com.nttdata.idmccnobe.dto.VoucherShortDto;
import com.nttdata.idmccnobe.service.CouponingService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author EFERRASXG
 */
@RestController
@RequestMapping(AbstractRestController.URI_REST + "/couponing")
public class CouponingRestController extends AbstractRestController {

    protected Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    @Autowired
    private CouponingService couponingService;

    @PostMapping(value = "/searchCoupon")
    public Object searchCoupon(@ModelAttribute("searchCashbackRequest") CouponingSearchRequestDto searchCashbackRequest, @RequestParam(value = "output", required = false) String output) {
        SearchCouponResDto res = new SearchCouponResDto();
        List<VoucherShortDto> voucherList = new ArrayList<>();
        try {
            if(searchCashbackRequest!=null && couponingService.checkSearchRequest(searchCashbackRequest)){
            voucherList = couponingService.searchVoucher(searchCashbackRequest);
            }else{
                voucherList = couponingService.searchAllVoucher();
            }
            SearchCouponPayloadDto payload = new SearchCouponPayloadDto();
            payload.setCouponingList(voucherList);
            res.getHeader().setResult("OK");
            res.setPayload(payload);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            res.getHeader().setResult("KO");
            res.getHeader().setErrorMessage("Errore durante l'operazione couponing");
        }
        
        return res;
    }
    
    @PostMapping(value="/delete")
    public Object deleteCoupon(@RequestParam("id") String idVoucher, @RequestParam(value="output", required = false) String output ){
        
        DeleteCouponResDto result = new DeleteCouponResDto();
        try {
            result = couponingService.deleteCoupon(idVoucher);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.getHeader().setResult("KO");
            result.getHeader().setErrorMessage("Errore durante l'operazione couponing");
        }
        return result;
    }
    
    @PostMapping(value="/modifyVoucher")
    public Object updateVoucher(@RequestBody VoucherDto voucherToSave, @RequestParam(value="output", required = false) String output ){

        ModifyVoucherDto result = new ModifyVoucherDto();
        String voucherId = voucherToSave != null ? voucherToSave.getIdVoucher() : null;
        int imageSize = voucherToSave != null && voucherToSave.getFileInput() != null
                ? voucherToSave.getFileInput().length
                : 0;
        logger.info("Ricevuta richiesta REST modifyVoucher. voucherId=" + voucherId
                + ", immaginePresente=" + (imageSize > 0)
                + ", dimensioneImmagineBytes=" + imageSize);
        try {
            result = couponingService.modifyVoucher(voucherToSave);
            logger.info("Richiesta REST modifyVoucher completata. voucherId=" + voucherId
                    + ", result=" + (result.getHeader() != null ? result.getHeader().getResult() : null));
        } catch (Exception ex) {
            logger.error("Errore non gestito nella richiesta REST modifyVoucher. voucherId=" + voucherId
                    + ", errore=" + ex.getMessage(), ex);
            result.getHeader().setResult("KO");
            result.getHeader().setErrorMessage("Errore durante l'operazione couponing");
        }
        return result;
    }
}
