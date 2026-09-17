/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author BernardisMa
 */
@Component
@Scope("session")
public class SearchCashbackResDto extends HeaderDto implements Serializable {

    private static final long serialVersionUID = 8855588693946900185L;
    
    private SearchCashbackResPayloadDto payload = new SearchCashbackResPayloadDto();

    public SearchCashbackResDto(){}
    
    public SearchCashbackResDto(CommonDto header){
        this.header = header;
    }
    
    public SearchCashbackResDto(CommonDto header, SearchCashbackResPayloadDto payload){
         this.header = header;
         this.payload = payload;
    }
    
    public SearchCashbackResPayloadDto getPayload() {
        return payload;
    }

    public void setPayload(SearchCashbackResPayloadDto payload) {
        this.payload = payload;
    }
    
}
