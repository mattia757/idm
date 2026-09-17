/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.service;

import com.nttdata.idmccnobe.dao.CouponingDao;
import com.nttdata.idmccnobe.dao.CouponingRedPromoTypeDao;
import com.nttdata.idmccnobe.dto.CommonDto;
import com.nttdata.idmccnobe.dto.CouponingRedPromoTypeDto;
import com.nttdata.idmccnobe.dto.CouponingSearchRequestDto;
import com.nttdata.idmccnobe.dto.DeleteCouponResDto;
import com.nttdata.idmccnobe.dto.ModifyVoucherDto;
import com.nttdata.idmccnobe.dto.VoucherDto;
import com.nttdata.idmccnobe.dto.VoucherShortDto;
import com.nttdata.idmccnobe.model.CouponingRedPromoType;
import com.nttdata.idmccnobe.model.CouponingVoucher;
import com.nttdata.idmccnobe.util.Constants;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author EFERRASXG
 */
@Service("CouponingService")
@Transactional
public class CouponingService extends AbstractService {
    
    @Autowired
    private CouponingDao couponingDao;
    
    @Autowired
    private CouponingRedPromoTypeDao couponingRedPromoTypeDao;

    @Autowired
    private CouponImageService couponImageService;
    
    public List<VoucherShortDto> searchVoucher(CouponingSearchRequestDto request) {
        List<CouponingVoucher> voucherEntity = couponingDao.searchVoucher(request);
        List<VoucherShortDto> response = new ArrayList<>();
        for(CouponingVoucher actual : voucherEntity){
            VoucherShortDto toAppend = actual.convertToShortDto();
            response.add(toAppend);
        }
        return response;
    }
    
    public List<VoucherShortDto> searchAllVoucher(){
        List<CouponingVoucher> voucherEntity = couponingDao.findAll();
        List<VoucherShortDto> response = new ArrayList<>();
        for(CouponingVoucher actual : voucherEntity){
            VoucherShortDto toAppend = actual.convertToShortDto();
            response.add(toAppend);
        }
        return response;
    }
    
    public boolean checkSearchRequest(CouponingSearchRequestDto searchCashbackRequest){
        boolean res = true;
        if(searchCashbackRequest.getVoucherId()==null || searchCashbackRequest.getVoucherId().isEmpty()){
            if(searchCashbackRequest.getBarcode()==null || searchCashbackRequest.getBarcode().isEmpty()){
                if(searchCashbackRequest.getVoucherDate()==null || searchCashbackRequest.getVoucherDate().isEmpty()){
                    res = false;
                }
            }
        }        
        return res;
    }
    
    public List<CouponingRedPromoTypeDto> findAllRedPromoType(){
        List<CouponingRedPromoTypeDto> res =  new ArrayList<>();
        List<CouponingRedPromoType> promoTypes = couponingRedPromoTypeDao.findAll();
        for(CouponingRedPromoType actual : promoTypes){
            CouponingRedPromoTypeDto toAdd = actual.toDto();
            res.add(toAdd);
        }
        return res;
    }
    
    public DeleteCouponResDto deleteCoupon(String voucherId) {
        DeleteCouponResDto res = new DeleteCouponResDto();
        if (voucherId == null || voucherId.trim().isEmpty()) {
            res.setHeader(new CommonDto(CommonDto.ACK_KO, 11, "Voucher ID mancante o non valido"));
            return res;
        }
        try {
            CouponingVoucher voucherToDelete = couponingDao.findById(voucherId);
            if (voucherToDelete != null) {
                couponingDao.delete(voucherToDelete);
                res.setHeader(new CommonDto(CommonDto.ACK_OK, null, ""));
            } else {
                res.setHeader(new CommonDto(CommonDto.ACK_KO, 17, "Voucher not found"));
            }
        } catch (Exception ex) {            
            res.setHeader(new CommonDto(CommonDto.ACK_KO, 17, "Errore durante l'eliminazione: " + ex.getMessage()));
        }
        return res;
    }
    
    public VoucherDto getVoucherById(String voucherId){
        VoucherDto res = new VoucherDto();
        CouponingVoucher voucherEntity = couponingDao.findById(voucherId);
        if(voucherEntity!=null){        
            res = voucherEntity.convertToDto();
        }        
        return res;
    } 
    
    public ModifyVoucherDto modifyVoucher(VoucherDto voucherToSave) {
        ModifyVoucherDto res = new ModifyVoucherDto();
        String voucherId = voucherToSave != null ? voucherToSave.getIdVoucher() : null;
        int imageSize = voucherToSave != null && voucherToSave.getFileInput() != null
                ? voucherToSave.getFileInput().length
                : 0;
        logger.info("Avvio modifica voucher. voucherId=" + voucherId
                + ", immaginePresente=" + (imageSize > 0)
                + ", dimensioneImmagineBytes=" + imageSize);

        if (voucherToSave != null && voucherToSave.getIdVoucher() != null && !voucherToSave.getIdVoucher().isEmpty()) {
            CouponingVoucher voucherEntityToModify = couponingDao.findById(voucherToSave.getIdVoucher());

            if (voucherEntityToModify != null) {
                logger.info("Voucher trovato sul DB. voucherId=" + voucherId);
                try {
                    String couponImageUrl = null;
                    if (voucherToSave.getFileInput() != null && voucherToSave.getFileInput().length > 0) {
                        logger.info("Nuova immagine presente: avvio upload. voucherId=" + voucherId);
                        couponImageUrl = couponImageService.saveVoucherImage(
                                voucherToSave.getIdVoucher(),
                                voucherToSave.getFileInput()
                        );
                        logger.info("Upload immagine completato: URL pubblico pronto per il DB. voucherId="
                                + voucherId + ", imageUrl=" + couponImageUrl);
                    } else {
                        logger.info("Nessuna nuova immagine: URL esistente mantenuto. voucherId=" + voucherId);
                    }

                    // MODIFICABILI: campi NON di CRM
                    voucherEntityToModify.setHeaderMsg(voucherToSave.getHeaderMsg());
                    voucherEntityToModify.setBcdEanDiscCoup8(voucherToSave.getBcdEanDiscCoup8());
                    voucherEntityToModify.setValueType(voucherToSave.getValueType());
                    voucherEntityToModify.setValue(
                            voucherToSave.getValue() != null ? new BigDecimal(voucherToSave.getValue()) : null
                    );
                    voucherEntityToModify.setRedStartDate(parseDate(voucherToSave.getRedStartDate()));
                    voucherEntityToModify.setRedEndDate(parseDate(voucherToSave.getRedEndDate()));
                    voucherEntityToModify.setCommDescr(voucherToSave.getCommDescr());
                    voucherEntityToModify.setCommType(voucherToSave.getCommType());
                    voucherEntityToModify.setRedPromoType(voucherToSave.getRedPromoType());
                    voucherEntityToModify.setCodice(voucherToSave.getCodice());
                    voucherEntityToModify.setLink(voucherToSave.getLink());
                    if (couponImageUrl != null) {
                        voucherEntityToModify.setCouponImageUrl(couponImageUrl);
                    }
                    voucherEntityToModify.setDescrizioneWeb(voucherToSave.getDescrizioneWeb());
                    voucherEntityToModify.setMessaggioCarta(voucherToSave.getMessaggioCarta());
                    voucherEntityToModify.setTermsAndConditions(voucherToSave.getTermsAndConditions());
                    voucherEntityToModify.setTag(voucherToSave.getTag());
                    voucherEntityToModify.setAttivo(voucherToSave.getAttivo().equalsIgnoreCase(Constants.COUPONING_ATTIVO));

                    // PERSISTI
                    logger.info("Avvio persistenza dati voucher sul DB. voucherId=" + voucherId
                            + ", aggiornamentoImageUrl=" + (couponImageUrl != null));
                    couponingDao.saveOrUpdate(voucherEntityToModify);
                    logger.info("Persistenza voucher completata con successo. voucherId=" + voucherId);

                    // ACK positivo
                    res.setHeader(new CommonDto(CommonDto.ACK_OK, null, ""));
                } catch (Exception e) {
                    logger.error("Errore durante la modifica del voucher. voucherId=" + voucherId
                            + ", errore=" + e.getMessage(), e);
                    res.setHeader(new CommonDto(CommonDto.ACK_KO, 17, "Errore durante l'update: " + e.getMessage()));
                }
            } else {
                logger.error("Modifica voucher non eseguita: voucher non trovato. voucherId=" + voucherId);
                res.setHeader(new CommonDto(CommonDto.ACK_KO, 17, "Voucher non trovato"));
            }
        } else {
            logger.error("Modifica voucher non eseguita: DTO o voucherId non valido. voucherId=" + voucherId);
            res.setHeader(new CommonDto(CommonDto.ACK_KO, 17, "Voucher DTO non valido"));
        }

        logger.info("Fine modifica voucher. voucherId=" + voucherId
                + ", result=" + (res.getHeader() != null ? res.getHeader().getResult() : null));

        return res;
    }

    
  private Date parseDate(String dateStr) {
    if (dateStr == null || dateStr.trim().isEmpty()) return null;
    try {
        return new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
    } catch (ParseException e) {
        // Log eventualmente
        return null;
    }
}

    
}
