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
public class SaveCashbackPayloadDto extends CommonPayload implements Serializable {
    
    private static final long serialVersionUID = -5456967365702954934L;
    
    Integer cashbackIdNew;

    public SaveCashbackPayloadDto() {
    }

    public SaveCashbackPayloadDto(Integer cashbackIdNew) {
        this.cashbackIdNew = cashbackIdNew;
    }

    public Integer getCashbackIdNew() {
        return cashbackIdNew;
    }

    public void setCashbackIdNew(Integer cashbackIdNew) {
        this.cashbackIdNew = cashbackIdNew;
    }
    
}
