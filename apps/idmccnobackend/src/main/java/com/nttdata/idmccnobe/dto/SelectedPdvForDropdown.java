/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author BernardisMa
 */
public class SelectedPdvForDropdown implements Serializable{

    private static final long serialVersionUID = 2820423712786477440L;
    
    private String id;
    private String selected;
    private String value;

    public SelectedPdvForDropdown() {
    }

    public SelectedPdvForDropdown(String id, String selected, String value) {
        this.id = id;
        this.selected = selected;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
