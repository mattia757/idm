/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author BernardisMa
 */
@Component
@Scope("session")
public class AnagraficaCashbackResDto extends HeaderDto implements Serializable {

    private static final long serialVersionUID = 8855588693946900185L;
    
    AnagraficaCashbackResPayloadDto payload = new AnagraficaCashbackResPayloadDto();
    
    public AnagraficaCashbackResDto() {
    }
    
    public AnagraficaCashbackResDto(CommonDto header) {
        this.header = header;
    }

    public AnagraficaCashbackResDto(CommonDto header, AnagraficaCashbackResPayloadDto payload) {
        this.header = header;
        this.payload = payload;
    }

    public AnagraficaCashbackResPayloadDto getPayload() {
        return payload;
    }

    public void setPayload(AnagraficaCashbackResPayloadDto payload) {
        this.payload = payload;
    }
    
}
