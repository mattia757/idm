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
public class SearchCashbackRequest implements Serializable{

    private static final long serialVersionUID = 2820423712786477440L;
    
    private String id;
    private String titolo;
    private String statoCashback;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getStatoCashback() {
        return statoCashback;
    }

    public void setStatoCashback(String statoCashback) {
        this.statoCashback = statoCashback;
    }
    
}
