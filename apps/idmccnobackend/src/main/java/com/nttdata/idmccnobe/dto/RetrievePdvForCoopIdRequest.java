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
public class RetrievePdvForCoopIdRequest implements Serializable{

    private static final long serialVersionUID = 2820423712786477440L;
    
    private List<Integer> coopIdList;

    public RetrievePdvForCoopIdRequest() {
    }

    public RetrievePdvForCoopIdRequest(List<Integer> coopIdList) {
        this.coopIdList = coopIdList;
    }

    public List<Integer> getCoopIdList() {
        return coopIdList;
    }

    public void setCoopIdList(List<Integer> coopIdList) {
        this.coopIdList = coopIdList;
    }
    
}
