/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author BernardisMa
 */
public class SearchCashbackCompleteDataPayloadDto extends CommonPayload implements Serializable {

    private static final long serialVersionUID = -401941021311761581L;
    
    private List<CashbackFormatted> cashbackList;

    public SearchCashbackCompleteDataPayloadDto() {
    }

    public SearchCashbackCompleteDataPayloadDto(List<CashbackFormatted> cashbackList) {
        this.cashbackList = cashbackList;
    }

    public List<CashbackFormatted> getCashbackList() {
        return cashbackList;
    }

    public void setCashbackList(List<CashbackFormatted> cashbackList) {
        this.cashbackList = cashbackList;
    }
    
}