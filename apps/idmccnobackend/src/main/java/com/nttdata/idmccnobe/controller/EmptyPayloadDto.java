/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.CommonPayload;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author BernardisMa
 */
@Component
@Scope("session")
public class EmptyPayloadDto extends CommonPayload implements Serializable {

    private static final long serialVersionUID = -401941021311761581L;
    
}
