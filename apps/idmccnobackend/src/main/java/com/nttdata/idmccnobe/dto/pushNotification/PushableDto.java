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
public class PushableDto implements Serializable {

    private static final long serialVersionUID = 7570081134162246533L;
    
    private int number;
    private String technologyClass;

    public PushableDto() {
    }

    public PushableDto(int number, String technologyClass) {
        this.number = number;
        this.technologyClass = technologyClass;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTechnologyClass() {
        return technologyClass;
    }

    public void setTechnologyClass(String technologyClass) {
        this.technologyClass = technologyClass;
    }
    
}
