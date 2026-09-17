/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto.pushNotification;

import com.nttdata.idmccnobe.dto.CommonDto;
import com.nttdata.idmccnobe.dto.CommonNotificationsDto;
import com.nttdata.idmccnobe.dto.HeaderDto;
import com.nttdata.idmccnobe.dto.PushNotificationHeaderDto;
import java.io.Serializable;

/**
 *
 * @author BernardisMa
 */
public class PushNotificationResponse extends PushNotificationHeaderDto implements Serializable {

    private static final long serialVersionUID = -4957313396988117913L;
    
    private PushNotificationResponsePayloadDto payload;

    public PushNotificationResponse() {
    }

    public PushNotificationResponse(PushNotificationResponsePayloadDto payload) {
        this.payload = payload;
    }

    public PushNotificationResponsePayloadDto getPayload() {
        return payload;
    }

    public void setPayload(PushNotificationResponsePayloadDto payload) {
        this.payload = payload;
    }

    public CommonNotificationsDto getStatus() {
        return status;
    }

    public void setStatus(CommonNotificationsDto status) {
        this.status = status;
    }    
    
}
