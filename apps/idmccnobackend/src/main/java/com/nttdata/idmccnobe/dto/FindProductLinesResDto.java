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
public class FindProductLinesResDto extends HeaderDto implements Serializable {

    private static final long serialVersionUID = 8855588693946900185L;

    FindProductLinesResPayloadDto payload = new FindProductLinesResPayloadDto();
    
    public FindProductLinesResDto() {
    }

    public FindProductLinesResDto(CommonDto header, FindProductLinesResPayloadDto payload) {
        this.header = header;
        this.payload = payload;
    }

    public FindProductLinesResPayloadDto getPayload() {
        return payload;
    }

    public void setPayload(FindProductLinesResPayloadDto payload) {
        this.payload = payload;
    }
    
}
