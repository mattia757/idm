/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.model;

import com.nttdata.idmccnobe.dto.CouponingRedPromoTypeDto;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author EFERRASXG
 */
@Entity
@Table(name = "CCNO_COUPONING_RED_PROMO_TYPE")
public class CouponingRedPromoType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "descrizione")
    private String descrizione;

    public CouponingRedPromoType() {}

    public CouponingRedPromoType(String code, String descrizione) {
        this.code = code;
        this.descrizione = descrizione;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
    public CouponingRedPromoTypeDto toDto(){
        CouponingRedPromoTypeDto res = new CouponingRedPromoTypeDto();
        res.setCodice(this.code);
        res.setDescrizione(this.descrizione);
        return res;
    }
}
