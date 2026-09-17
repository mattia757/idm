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
public class PushNotificationResponsePayloadDto implements Serializable {

    private static final long serialVersionUID = 7570081134162246533L;
    
    private String expire;
    private String follow;
    private String mode;
    private String noFeedback;
    private List<PackedDto> packed;
    private int packedTotal;
    private List<PushableDto> pushable;
    private int pushedTotal;
    private String reality;
    private String token;

    public PushNotificationResponsePayloadDto() {
    }

    public PushNotificationResponsePayloadDto(String expire, String follow, String mode, String noFeedback, List<PackedDto> packed, int packedTotal, List<PushableDto> pushable, int pushedTotal, String reality, String token) {
        this.expire = expire;
        this.follow = follow;
        this.mode = mode;
        this.noFeedback = noFeedback;
        this.packed = packed;
        this.packedTotal = packedTotal;
        this.pushable = pushable;
        this.pushedTotal = pushedTotal;
        this.reality = reality;
        this.token = token;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getNoFeedback() {
        return noFeedback;
    }

    public void setNoFeedback(String noFeedback) {
        this.noFeedback = noFeedback;
    }

    public List<PackedDto> getPacked() {
        return packed;
    }

    public void setPacked(List<PackedDto> packed) {
        this.packed = packed;
    }

    public int getPackedTotal() {
        return packedTotal;
    }

    public void setPackedTotal(int packedTotal) {
        this.packedTotal = packedTotal;
    }

    public List<PushableDto> getPushable() {
        return pushable;
    }

    public void setPushable(List<PushableDto> pushable) {
        this.pushable = pushable;
    }

    public int getPushedTotal() {
        return pushedTotal;
    }

    public void setPushedTotal(int pushedTotal) {
        this.pushedTotal = pushedTotal;
    }

    public String getReality() {
        return reality;
    }

    public void setReality(String reality) {
        this.reality = reality;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
    
}
