/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto.pushNotification;

import java.io.Serializable;

/**
 *
 * @author BernardisMa
 */
public class ApplicationDto implements Serializable {

    private static final long serialVersionUID = 7570081134162246533L;
    
    private String code;

    public ApplicationDto() {
    }

    public ApplicationDto(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
