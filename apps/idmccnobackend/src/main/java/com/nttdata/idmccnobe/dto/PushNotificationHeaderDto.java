/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author FerrandicoSi
 */
public class PushNotificationHeaderDto implements Serializable {
    
    private static final long serialVersionUID = 3018325710928466212L;
    
    protected CommonNotificationsDto status = new CommonNotificationsDto();

    public PushNotificationHeaderDto() {
    }

    public CommonNotificationsDto getStatus() {
        return status;
    }

    public void setStatus(CommonNotificationsDto status) {
        this.status = status;
    }
    
    
}
