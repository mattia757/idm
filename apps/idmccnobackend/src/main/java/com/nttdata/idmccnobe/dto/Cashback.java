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
public class Cashback implements Serializable{

    private static final long serialVersionUID = -8732603145820885405L;
    
    private int id;
    private Boolean activePdv;
    private String title;
    private String description;
    private String status;
    private String conditionsDescription;
    private String conditionsValidityDescription;
    private AccumulationDto accumulationDetail;
    private FruitionDto fruitionDetail;

    public Cashback() {
    }

    public Cashback(int id, Boolean activePdv, String title, String description, String status, String conditionsDescription, String conditionsValidityDescription, AccumulationDto accumulationDetail, FruitionDto fruitionDetail) {
        this.id = id;
        this.activePdv = activePdv;
        this.title = title;
        this.description = description;
        this.status = status;
        this.conditionsDescription = conditionsDescription;
        this.conditionsValidityDescription = conditionsValidityDescription;
        this.accumulationDetail = accumulationDetail;
        this.fruitionDetail = fruitionDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getActivePdv() {
        return activePdv;
    }

    public void setActivePdv(Boolean activePdv) {
        this.activePdv = activePdv;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConditionsDescription() {
        return conditionsDescription;
    }

    public void setConditionsDescription(String conditionsDescription) {
        this.conditionsDescription = conditionsDescription;
    }

    public String getConditionsValidityDescription() {
        return conditionsValidityDescription;
    }

    public void setConditionsValidityDescription(String conditionsValidityDescription) {
        this.conditionsValidityDescription = conditionsValidityDescription;
    }

    public AccumulationDto getAccumulationDetail() {
        return accumulationDetail;
    }

    public void setAccumulationDetail(AccumulationDto accumulationDetail) {
        this.accumulationDetail = accumulationDetail;
    }

    public FruitionDto getFruitionDetail() {
        return fruitionDetail;
    }

    public void setFruitionDetail(FruitionDto fruitionDetail) {
        this.fruitionDetail = fruitionDetail;
    }
    
}
