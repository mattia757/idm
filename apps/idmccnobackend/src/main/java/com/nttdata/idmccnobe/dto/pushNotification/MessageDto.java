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
public class MessageDto implements Serializable {

    private static final long serialVersionUID = 7570081134162246533L;
    
    private int badge;
    private boolean hidden;
    private int life;
    private String sound;
    private String text;
    private MessageBodyDto body;

    public MessageDto() {
    }

    public MessageDto(int badge, boolean hidden, int life, String sound, String text, MessageBodyDto body) {
        this.badge = badge;
        this.hidden = hidden;
        this.life = life;
        this.sound = sound;
        this.text = text;
        this.body = body;
    }

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageBodyDto getBody() {
        return body;
    }

    public void setBody(MessageBodyDto body) {
        this.body = body;
    }
    
}
