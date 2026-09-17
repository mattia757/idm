/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author BernardisMa
 */
@Component
@Scope("session")
public class SearchCashbackResPayloadDto extends CommonPayload implements Serializable {

    private static final long serialVersionUID = -401941021311761581L;
    
    private List<Cashback> cashbackList;

    public SearchCashbackResPayloadDto() {
    }

    public SearchCashbackResPayloadDto(List<Cashback> cashbackList) {
        this.cashbackList = cashbackList;
    }

    public List<Cashback> getCashbackList() {
        return cashbackList;
    }

    public void setCashbackList(List<Cashback> cashbackList) {
        this.cashbackList = cashbackList;
    }
    
}
