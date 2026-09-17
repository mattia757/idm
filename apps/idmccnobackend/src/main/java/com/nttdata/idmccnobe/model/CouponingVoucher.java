/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.model;

import com.nttdata.idmccnobe.dto.VoucherDto;
import com.nttdata.idmccnobe.dto.VoucherShortDto;
import com.nttdata.idmccnobe.util.Constants;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

/**
 *
 * @author EFERRASXG
 */

@Entity
@Table(name = "CCNO_COUPONING_VOUCHER")
public class CouponingVoucher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "IDVOUCHER")
    private String idVoucher;

    @Column(name = "IDSTATEFK", nullable = false)
    private String idStateFk;

    @Column(name = "IDTYPE", nullable = false)
    private String idType;

    @Column(name = "HEADERMSG")
    private String headerMsg;

    @Column(name = "BCDEANDISCOUP8")
    private String bcdEanDiscCoup8;

    @Column(name = "VALUETYPE")
    private String valueType;

    @Column(name = "VOUCHERTYPE")
    private String voucherType;

    @Column(name = "COUPCOUNTER")
    private Integer coupCounter;

    @Column(name = "VALUE")
    private BigDecimal value;

    @Temporal(TemporalType.DATE)
    @Column(name = "REDSTARTDATE")
    private Date redStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "REDENDDATE")
    private Date redEndDate;

    @Column(name = "COMMDESCR")
    private String commDescr;

    @Column(name = "COMMTYPE", nullable = false)
    private String commType;

    @Column(name = "REDPROMOTYPE")
    private String redPromoType;

    @Size(max = 255)
    @Column(name = "CODICE", length = 255)
    private String codice;

    @Size(max = 2048)
    @Column(name = "LINK", length = 2048)
    private String link;

    @Size(max = 2048)
    @Column(name = "COUPON_IMAGE_URL", length = 2048)
    private String couponImageUrl;
    
    @Size(max = 4000)
    @Column(name = "DESCRIZIONEWEB")
    private String descrizioneWeb;
    
    @Size(max = 4000)
    @Column(name = "MESSAGGIOCARTA")
    private String messaggioCarta;

    @Size(max = 4000)
    @Column(name = "TERMSANDCONDITIONS")
    private String termsAndConditions;

    @Column(name = "TAG")
    private String tag;

    @Column(name = "DESCRIPTIONCRM")
    private String descriptionCrm;

    @Temporal(TemporalType.DATE)
    @Column(name = "STARTDATECRM")
    private Date startDateCrm;

    @Temporal(TemporalType.DATE)
    @Column(name = "ENDDATACRM")
    private Date endDateCrm;

    @Column(name = "NOTECRM")
    private String noteCrm;
    
    @Column(name = "ATTIVO", nullable = false)
    private Boolean attivo;
    
    public CouponingVoucher() {
    }

    public String getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(String idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getIdStateFk() {
        return idStateFk;
    }

    public void setIdStateFk(String idStateFk) {
        this.idStateFk = idStateFk;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getHeaderMsg() {
        return headerMsg;
    }

    public void setHeaderMsg(String headerMsg) {
        this.headerMsg = headerMsg;
    }

    public String getBcdEanDiscCoup8() {
        return bcdEanDiscCoup8;
    }

    public void setBcdEanDiscCoup8(String bcdEanDiscCoup8) {
        this.bcdEanDiscCoup8 = bcdEanDiscCoup8;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public Integer getCoupCounter() {
        return coupCounter;
    }

    public void setCoupCounter(Integer coupCounter) {
        this.coupCounter = coupCounter;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getRedStartDate() {
        return redStartDate;
    }

    public void setRedStartDate(Date redStartDate) {
        this.redStartDate = redStartDate;
    }

    public Date getRedEndDate() {
        return redEndDate;
    }

    public void setRedEndDate(Date redEndDate) {
        this.redEndDate = redEndDate;
    }

    public String getCommDescr() {
        return commDescr;
    }

    public void setCommDescr(String commDescr) {
        this.commDescr = commDescr;
    }

    public String getCommType() {
        return commType;
    }

    public void setCommType(String commType) {
        this.commType = commType;
    }

    public String getRedPromoType() {
        return redPromoType;
    }

    public void setRedPromoType(String redPromoType) {
        this.redPromoType = redPromoType;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCouponImageUrl() {
        return couponImageUrl;
    }

    public void setCouponImageUrl(String couponImageUrl) {
        this.couponImageUrl = couponImageUrl;
    }

    public String getDescrizioneWeb() {
        return descrizioneWeb;
    }

    public void setDescrizioneWeb(String descrizioneWeb) {
        this.descrizioneWeb = descrizioneWeb;
    }

    public String getMessaggioCarta() {
        return messaggioCarta;
    }

    public void setMessaggioCarta(String messaggioCarta) {
        this.messaggioCarta = messaggioCarta;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescriptionCrm() {
        return descriptionCrm;
    }

    public void setDescriptionCrm(String descriptionCrm) {
        this.descriptionCrm = descriptionCrm;
    }

    public Date getStartDateCrm() {
        return startDateCrm;
    }

    public void setStartDateCrm(Date startDateCrm) {
        this.startDateCrm = startDateCrm;
    }

    public Date getEndDateCrm() {
        return endDateCrm;
    }

    public void setEndDateCrm(Date endDateCrm) {
        this.endDateCrm = endDateCrm;
    }

    public String getNoteCrm() {
        return noteCrm;
    }

    public void setNoteCrm(String noteCrm) {
        this.noteCrm = noteCrm;
    }    

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }
    
    
    public VoucherShortDto convertToShortDto(){
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        VoucherShortDto res = new VoucherShortDto();

        res.setIdVoucher(this.getIdVoucher());
        res.setIdType(this.getIdType());
        res.setHeaderMSG(this.getHeaderMsg());
        res.setVoucherType(this.getVoucherType());
        if (this.getRedStartDate() != null) {
            res.setDataInizio(sdf.format(this.getRedStartDate()));
        }
        if (this.getRedEndDate() != null) {
            res.setDataFine(sdf.format(this.getRedEndDate()));
        }
        res.setValue(String.valueOf(this.getValue()));
        
        return res;
    }
    
    public VoucherDto convertToDto() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        VoucherDto dto = new VoucherDto();

        dto.setIdVoucher(this.idVoucher);
        dto.setIdStateFk(this.idStateFk);
        dto.setIdType(this.idType);
        dto.setHeaderMsg(this.headerMsg);
        dto.setBcdEanDiscCoup8(this.bcdEanDiscCoup8);
        dto.setValueType(this.valueType);
        dto.setVoucherType(this.voucherType);
        dto.setCoupCounter(this.coupCounter != null ? String.valueOf(this.coupCounter) : null);
        dto.setValue(this.value != null ? this.value.toPlainString() : null);
        dto.setRedStartDate(this.redStartDate != null ? sdf.format(this.redStartDate) : null);
        dto.setRedEndDate(this.redEndDate != null ? sdf.format(this.redEndDate) : null);
        dto.setCommDescr(this.commDescr);
        dto.setCommType(this.commType);
        dto.setRedPromoType(this.redPromoType);
        dto.setCodice(this.codice);
        dto.setLink(this.link);
        dto.setCouponImageUrl(this.couponImageUrl);
        dto.setDescrizioneWeb(this.descrizioneWeb);
        dto.setMessaggioCarta(this.messaggioCarta);
        dto.setTermsAndConditions(this.termsAndConditions);
        dto.setTag(this.tag);
        dto.setDescriptionCrm(this.descriptionCrm);
        dto.setStartDateCrm(this.startDateCrm != null ? sdf.format(this.startDateCrm) : null);
        dto.setEndDateCrm(this.endDateCrm != null ? sdf.format(this.endDateCrm) : null);
        dto.setNoteCrm(this.noteCrm);
        dto.setAttivo(this.attivo!=null? (this.attivo? Constants.COUPONING_ATTIVO : Constants.COUPONING_INATTIVO) : null );
        return dto;
    }
    
}
