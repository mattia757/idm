/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto.pushNotification;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author BernardisMa
 */
public class MessageBodyDto implements Serializable {

    private static final long serialVersionUID = 7570081134162246533L;
    
    private List<MessageBodyParamDto> params;
    private String type;

    public MessageBodyDto() {
    }

    public MessageBodyDto(List<MessageBodyParamDto> params, String type) {
        this.params = params;
        this.type = type;
    }

    public List<MessageBodyParamDto> getParams() {
        return params;
    }

    public void setParams(List<MessageBodyParamDto> params) {
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
