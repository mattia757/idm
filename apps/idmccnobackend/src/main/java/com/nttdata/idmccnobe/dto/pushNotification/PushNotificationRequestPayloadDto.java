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
public class PushNotificationRequestPayloadDto implements Serializable {

    private static final long serialVersionUID = 7570081134162246533L;
    
    private ApplicationDto application;
    private FilterDto filter;
    private MessageDto message;
    private boolean noFeedback;
    private boolean simulate;
    private List<String> remoteTag;

    public PushNotificationRequestPayloadDto() {
    }

    public PushNotificationRequestPayloadDto(ApplicationDto application, FilterDto filter, MessageDto message, boolean noFeedback, boolean simulate, List<String> remoteTag) {
        this.application = application;
        this.filter = filter;
        this.message = message;
        this.noFeedback = noFeedback;
        this.simulate = simulate;
        this.remoteTag = remoteTag;
    }

    public ApplicationDto getApplication() {
        return application;
    }

    public void setApplication(ApplicationDto application) {
        this.application = application;
    }

    public FilterDto getFilter() {
        return filter;
    }

    public void setFilter(FilterDto filter) {
        this.filter = filter;
    }

    public MessageDto getMessage() {
        return message;
    }

    public void setMessage(MessageDto message) {
        this.message = message;
    }

    public boolean isNoFeedback() {
        return noFeedback;
    }

    public void setNoFeedback(boolean noFeedback) {
        this.noFeedback = noFeedback;
    }

    public boolean isSimulate() {
        return simulate;
    }

    public void setSimulate(boolean simulate) {
        this.simulate = simulate;
    }

    public List<String> getRemoteTag() {
        return remoteTag;
    }

    public void setRemoteTag(List<String> remoteTag) {
        this.remoteTag = remoteTag;
    }
    
}
