/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;

/**
 *
 * @author BernardisMa
 */
public class AccumulationDto implements Serializable {
    
    private static final long serialVersionUID = -5456967365702954934L;
    
    private String type;
    private String startDate;
    private String endDate;
    private String description;
    private double value;
    private List<LineDto> lines;
    private String imageFlyer;
    private String linkFlyer;
    private String linkFlyerPdf;

    public AccumulationDto() {
    }

    public AccumulationDto(String type, String startDate, String endDate, String description, double value, List<LineDto> lines, String imageFlyer, String linkFlyer, String linkFlyerPdf) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.value = value;
        this.lines = lines;
        this.imageFlyer = imageFlyer;
        this.linkFlyer = linkFlyer;
        this.linkFlyerPdf = linkFlyerPdf;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public List<LineDto> getLines() {
        return lines;
    }

    public void setLines(List<LineDto> lines) {
        this.lines = lines;
    }

    public String getImageFlyer() {
        return imageFlyer;
    }

    public void setImageFlyer(String imageFlyer) {
        this.imageFlyer = imageFlyer;
    }

    public String getLinkFlyer() {
        return linkFlyer;
    }

    public void setLinkFlyer(String linkFlyer) {
        this.linkFlyer = linkFlyer;
    }

    public String getLinkFlyerPdf() {
        return linkFlyerPdf;
    }

    public void setLinkFlyerPdf(String linkFlyerPdf) {
        this.linkFlyerPdf = linkFlyerPdf;
    }
    
}
