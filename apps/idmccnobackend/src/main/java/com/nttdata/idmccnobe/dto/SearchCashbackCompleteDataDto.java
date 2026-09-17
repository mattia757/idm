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
public class SearchCashbackCompleteDataDto  extends HeaderDto implements Serializable {

    private static final long serialVersionUID = 8855588693946900185L;
    
    private SearchCashbackCompleteDataPayloadDto payload = new SearchCashbackCompleteDataPayloadDto();

    public SearchCashbackCompleteDataDto(){}
    
    public SearchCashbackCompleteDataDto(CommonDto header){
        this.header = header;
    }
    
    public SearchCashbackCompleteDataDto(CommonDto header, SearchCashbackCompleteDataPayloadDto payload){
         this.header = header;
         this.payload = payload;
    }
    
    public SearchCashbackCompleteDataPayloadDto getPayload() {
        return payload;
    }

    public void setPayload(SearchCashbackCompleteDataPayloadDto payload) {
        this.payload = payload;
    }
}
