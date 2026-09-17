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
public class SaveCashbackRequest implements Serializable{

    private static final long serialVersionUID = 2820423712786477440L;
    
    private String id;
    private String cashbackTitle;
    private String cashbackDescription;
    private String cashbackValue;
    private String status;
    private String cashbackPublicationStartDate;
    private String cashbackPublicationEndDate;
    private String conditionsDescription;
    private String conditionsValidityDescription;
    private String accumulationDescription;
    private String accumulationType;
    private String accumulationStartDate;
    private String accumulationEndDate;
    private String accumulationFlyerPdfLink;
    private String accumulationFlyerPdfImageUrl;
    private String accumulationProductLines;
    private String fruitionDescription;
    private String fruitionType;
    private String fruitionStartDate;
    private String fruitionEndDate;
    private String fruitionFlyerPdfLink;
    private String fruitionFlyerPdfImageUrl;
    private String fruitionProductLines;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCashbackTitle() {
        return cashbackTitle;
    }

    public void setCashbackTitle(String cashbackTitle) {
        this.cashbackTitle = cashbackTitle;
    }

    public String getCashbackDescription() {
        return cashbackDescription;
    }

    public void setCashbackDescription(String cashbackDescription) {
        this.cashbackDescription = cashbackDescription;
    }

    public String getCashbackValue() {
        return cashbackValue;
    }

    public void setCashbackValue(String cashbackValue) {
        this.cashbackValue = cashbackValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCashbackPublicationStartDate() {
        return cashbackPublicationStartDate;
    }

    public void setCashbackPublicationStartDate(String cashbackPublicationStartDate) {
        this.cashbackPublicationStartDate = cashbackPublicationStartDate;
    }

    public String getCashbackPublicationEndDate() {
        return cashbackPublicationEndDate;
    }

    public void setCashbackPublicationEndDate(String cashbackPublicationEndDate) {
        this.cashbackPublicationEndDate = cashbackPublicationEndDate;
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

    public String getAccumulationDescription() {
        return accumulationDescription;
    }

    public void setAccumulationDescription(String accumulationDescription) {
        this.accumulationDescription = accumulationDescription;
    }

    public String getAccumulationType() {
        return accumulationType;
    }

    public void setAccumulationType(String accumulationType) {
        this.accumulationType = accumulationType;
    }

    public String getAccumulationStartDate() {
        return accumulationStartDate;
    }

    public void setAccumulationStartDate(String accumulationStartDate) {
        this.accumulationStartDate = accumulationStartDate;
    }

    public String getAccumulationEndDate() {
        return accumulationEndDate;
    }

    public void setAccumulationEndDate(String accumulationEndDate) {
        this.accumulationEndDate = accumulationEndDate;
    }

    public String getAccumulationFlyerPdfLink() {
        return accumulationFlyerPdfLink;
    }

    public void setAccumulationFlyerPdfLink(String accumulationFlyerPdfLink) {
        this.accumulationFlyerPdfLink = accumulationFlyerPdfLink;
    }

    public String getAccumulationFlyerPdfImageUrl() {
        return accumulationFlyerPdfImageUrl;
    }

    public void setAccumulationFlyerPdfImageUrl(String accumulationFlyerPdfImageUrl) {
        this.accumulationFlyerPdfImageUrl = accumulationFlyerPdfImageUrl;
    }

    public String getAccumulationProductLines() {
        return accumulationProductLines;
    }

    public void setAccumulationProductLines(String accumulationProductLines) {
        this.accumulationProductLines = accumulationProductLines;
    }

    public String getFruitionDescription() {
        return fruitionDescription;
    }

    public void setFruitionDescription(String fruitionDescription) {
        this.fruitionDescription = fruitionDescription;
    }

    public String getFruitionType() {
        return fruitionType;
    }

    public void setFruitionType(String fruitionType) {
        this.fruitionType = fruitionType;
    }

    public String getFruitionStartDate() {
        return fruitionStartDate;
    }

    public void setFruitionStartDate(String fruitionStartDate) {
        this.fruitionStartDate = fruitionStartDate;
    }

    public String getFruitionEndDate() {
        return fruitionEndDate;
    }

    public void setFruitionEndDate(String fruitionEndDate) {
        this.fruitionEndDate = fruitionEndDate;
    }

    public String getFruitionFlyerPdfLink() {
        return fruitionFlyerPdfLink;
    }

    public void setFruitionFlyerPdfLink(String fruitionFlyerPdfLink) {
        this.fruitionFlyerPdfLink = fruitionFlyerPdfLink;
    }

    public String getFruitionFlyerPdfImageUrl() {
        return fruitionFlyerPdfImageUrl;
    }

    public void setFruitionFlyerPdfImageUrl(String fruitionFlyerPdfImageUrl) {
        this.fruitionFlyerPdfImageUrl = fruitionFlyerPdfImageUrl;
    }

    public String getFruitionProductLines() {
        return fruitionProductLines;
    }

    public void setFruitionProductLines(String fruitionProductLines) {
        this.fruitionProductLines = fruitionProductLines;
    }
    
}
