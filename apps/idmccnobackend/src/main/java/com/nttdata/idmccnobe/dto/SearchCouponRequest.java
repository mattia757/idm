/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author EFERRASXG
 */
public class SearchCouponRequest implements Serializable {
    
    private static final long serialVersionUID = 2820423712786477440L;
    
    private String idVoucher;
    private String barCode;
    private Date dataVoucher;

    public SearchCouponRequest() {
    }

    public String getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(String idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Date getDataVoucher() {
        return dataVoucher;
    }

    public void setDataVoucher(Date dataVoucher) {
        this.dataVoucher = dataVoucher;
    }
    
    
    
}
