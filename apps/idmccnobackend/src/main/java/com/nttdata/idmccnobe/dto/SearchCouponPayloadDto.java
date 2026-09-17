/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EFERRASXG
 */
public class SearchCouponPayloadDto implements Serializable {
    
    private static final long serialVersionUID = 8855588693946900185L;
    
    private List<VoucherShortDto> couponingList = new ArrayList<>();

    public SearchCouponPayloadDto() {
    }

    public List<VoucherShortDto> getCouponingList() {
        return couponingList;
    }

    public void setCouponingList(List<VoucherShortDto> couponingList) {
        this.couponingList = couponingList;
    }    
    
    
}
