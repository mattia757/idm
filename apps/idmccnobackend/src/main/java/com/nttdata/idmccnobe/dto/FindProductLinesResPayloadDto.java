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
public class FindProductLinesResPayloadDto extends CommonPayload implements Serializable {
    
    private static final long serialVersionUID = -5456967365702954934L;
    
    private List<ProductLineDto> productLines;

    public FindProductLinesResPayloadDto() {
    }

    public FindProductLinesResPayloadDto(List<ProductLineDto> productLines) {
        this.productLines = productLines;
    }

    public List<ProductLineDto> getProductLines() {
        return productLines;
    }

    public void setProductLines(List<ProductLineDto> productLines) {
        this.productLines = productLines;
    }
    
}
