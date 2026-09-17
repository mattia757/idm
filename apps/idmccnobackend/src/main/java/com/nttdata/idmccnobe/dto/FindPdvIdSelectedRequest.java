/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author BernardisMa
 */
public class FindPdvIdSelectedRequest implements Serializable{

    private static final long serialVersionUID = 2820423712786477440L;
    
    private String coopId;
    private String cashbackId;

    public FindPdvIdSelectedRequest() {
    }

    public FindPdvIdSelectedRequest(String coopId, String cashbackId) {
        this.coopId = coopId;
        this.cashbackId = cashbackId;
    }

    public String getCoopId() {
        return coopId;
    }

    public void setCoopId(String coopId) {
        this.coopId = coopId;
    }

    public String getCashbackId() {
        return cashbackId;
    }

    public void setCashbackId(String cashbackId) {
        this.cashbackId = cashbackId;
    }
    
}
