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
public class AnagraficaCashbackResPayloadDto extends CommonPayload implements Serializable {

    private static final long serialVersionUID = -5456967365702954934L;
    
    private CashbackFromDb cashback;

    public AnagraficaCashbackResPayloadDto() {
    }

    public AnagraficaCashbackResPayloadDto(CashbackFromDb cashback) {
        this.cashback = cashback;
    }

    public CashbackFromDb getCashback() {
        return cashback;
    }

    public void setCashback(CashbackFromDb cashback) {
        this.cashback = cashback;
    }
    
}
