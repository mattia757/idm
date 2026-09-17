/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author EFERRASXG
 */
public class SearchCouponResDto extends HeaderDto implements Serializable {
    
    private static final long serialVersionUID = 8855588693946900185L;
    
    private SearchCouponPayloadDto payload = new SearchCouponPayloadDto() ;

    public SearchCouponResDto() {
    }
    
    public SearchCouponResDto(CommonDto header) {
        this.header = header;
    }

    public SearchCouponResDto(CommonDto header, SearchCouponPayloadDto payload) {
        this.header = header;
        this.payload = payload;
    }

    public SearchCouponPayloadDto getPayload() {
        return payload;
    }

    public void setPayload(SearchCouponPayloadDto payload) {
        this.payload = payload;
    }
    
    
    
    
}
