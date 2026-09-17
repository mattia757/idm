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
public class MessageBodyParamDto implements Serializable {

    private static final long serialVersionUID = 7570081134162246533L;
    
    private String key;
    private String value;

    public MessageBodyParamDto() {
    }

    public MessageBodyParamDto(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
