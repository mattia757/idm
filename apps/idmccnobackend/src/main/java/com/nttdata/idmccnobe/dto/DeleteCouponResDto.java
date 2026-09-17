/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import com.nttdata.idmccnobe.controller.EmptyPayloadDto;
import java.io.Serializable;

/**
 *
 * @author EFERRASXG
 */
public class DeleteCouponResDto extends HeaderDto implements Serializable {
    
    private static final long serialVersionUID = 8855588693946900185L;
    
    private EmptyPayloadDto payload = new EmptyPayloadDto();

    public DeleteCouponResDto() {
    }

    public DeleteCouponResDto(CommonDto header){
        this.header = header;
    }    
    
    public DeleteCouponResDto(CommonDto header, EmptyPayloadDto payload){
        this.header = header;
        this.payload = payload;
    }

    public EmptyPayloadDto getPayload() {
        return payload;
    }

    public void setPayload(EmptyPayloadDto payload) {
        this.payload = payload;
    }
    
    
}
