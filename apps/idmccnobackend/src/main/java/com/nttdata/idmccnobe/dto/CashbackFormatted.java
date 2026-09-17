/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author BernardisMa
 */
public class CashbackFormatted implements Serializable{

    private static final long serialVersionUID = -5456967365702954934L;
    
    private Integer cashbackid;
    private String cashbacktitle;
    private String cashbackdescription;
    private double cashbackvalue;
    private String cashbackpublicationstartdate;
    private String cashbackpublicationenddate;
    private double status;
    private String accumulationdescription;
    private String accumulationstartdate;
    private String accumulationenddate;
    private Integer accumulationtype;
    private String accumulationflyerpdflink;
    private String accumulationflyerpdfimageurl;
    private String fruitiondescription;
    private String fruitionstartdate;
    private String fruitionenddate;
    private Integer fruitiontype;
    private String fruitionflyerpdflink;
    private String fruitionflyerpdfimageurl;
    private String conditionsdescription;
    private String conditionsvaliditydescription;

    public CashbackFormatted() {
    }

    public CashbackFormatted(Integer cashbackid, String cashbacktitle, String cashbackdescription, double cashbackvalue, String cashbackpublicationstartdate, String cashbackpublicationenddate, double status, String accumulationdescription, String accumulationstartdate, String accumulationenddate, Integer accumulationtype, String accumulationflyerpdflink, String accumulationflyerpdfimageurl, String fruitiondescription, String fruitionstartdate, String fruitionenddate, Integer fruitiontype, String fruitionflyerpdflink, String fruitionflyerpdfimageurl, String conditionsdescription, String conditionsvaliditydescription) {
        this.cashbackid = cashbackid;
        this.cashbacktitle = cashbacktitle;
        this.cashbackdescription = cashbackdescription;
        this.cashbackvalue = cashbackvalue;
        this.cashbackpublicationstartdate = cashbackpublicationstartdate;
        this.cashbackpublicationenddate = cashbackpublicationenddate;
        this.status = status;
        this.accumulationdescription = accumulationdescription;
        this.accumulationstartdate = accumulationstartdate;
        this.accumulationenddate = accumulationenddate;
        this.accumulationtype = accumulationtype;
        this.accumulationflyerpdflink = accumulationflyerpdflink;
        this.accumulationflyerpdfimageurl = accumulationflyerpdfimageurl;
        this.fruitiondescription = fruitiondescription;
        this.fruitionstartdate = fruitionstartdate;
        this.fruitionenddate = fruitionenddate;
        this.fruitiontype = fruitiontype;
        this.fruitionflyerpdflink = fruitionflyerpdflink;
        this.fruitionflyerpdfimageurl = fruitionflyerpdfimageurl;
        this.conditionsdescription = conditionsdescription;
        this.conditionsvaliditydescription = conditionsvaliditydescription;
    }

    public Integer getCashbackid() {
        return cashbackid;
    }

    public void setCashbackid(Integer cashbackid) {
        this.cashbackid = cashbackid;
    }

    public String getCashbacktitle() {
        return cashbacktitle;
    }

    public void setCashbacktitle(String cashbacktitle) {
        this.cashbacktitle = cashbacktitle;
    }

    public String getCashbackdescription() {
        return cashbackdescription;
    }

    public void setCashbackdescription(String cashbackdescription) {
        this.cashbackdescription = cashbackdescription;
    }

    public double getCashbackvalue() {
        return cashbackvalue;
    }

    public void setCashbackvalue(double cashbackvalue) {
        this.cashbackvalue = cashbackvalue;
    }

    public String getCashbackpublicationstartdate() {
        return cashbackpublicationstartdate;
    }

    public void setCashbackpublicationstartdate(String cashbackpublicationstartdate) {
        this.cashbackpublicationstartdate = cashbackpublicationstartdate;
    }

    public String getCashbackpublicationenddate() {
        return cashbackpublicationenddate;
    }

    public void setCashbackpublicationenddate(String cashbackpublicationenddate) {
        this.cashbackpublicationenddate = cashbackpublicationenddate;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    public String getAccumulationdescription() {
        return accumulationdescription;
    }

    public void setAccumulationdescription(String accumulationdescription) {
        this.accumulationdescription = accumulationdescription;
    }

    public String getAccumulationstartdate() {
        return accumulationstartdate;
    }

    public void setAccumulationstartdate(String accumulationstartdate) {
        this.accumulationstartdate = accumulationstartdate;
    }

    public String getAccumulationenddate() {
        return accumulationenddate;
    }

    public void setAccumulationenddate(String accumulationenddate) {
        this.accumulationenddate = accumulationenddate;
    }

    public Integer getAccumulationtype() {
        return accumulationtype;
    }

    public void setAccumulationtype(Integer accumulationtype) {
        this.accumulationtype = accumulationtype;
    }

    public String getAccumulationflyerpdflink() {
        return accumulationflyerpdflink;
    }

    public void setAccumulationflyerpdflink(String accumulationflyerpdflink) {
        this.accumulationflyerpdflink = accumulationflyerpdflink;
    }

    public String getAccumulationflyerpdfimageurl() {
        return accumulationflyerpdfimageurl;
    }

    public void setAccumulationflyerpdfimageurl(String accumulationflyerpdfimageurl) {
        this.accumulationflyerpdfimageurl = accumulationflyerpdfimageurl;
    }

    public String getFruitiondescription() {
        return fruitiondescription;
    }

    public void setFruitiondescription(String fruitiondescription) {
        this.fruitiondescription = fruitiondescription;
    }

    public String getFruitionstartdate() {
        return fruitionstartdate;
    }

    public void setFruitionstartdate(String fruitionstartdate) {
        this.fruitionstartdate = fruitionstartdate;
    }

    public String getFruitionenddate() {
        return fruitionenddate;
    }

    public void setFruitionenddate(String fruitionenddate) {
        this.fruitionenddate = fruitionenddate;
    }

    public Integer getFruitiontype() {
        return fruitiontype;
    }

    public void setFruitiontype(Integer fruitiontype) {
        this.fruitiontype = fruitiontype;
    }

    public String getFruitionflyerpdflink() {
        return fruitionflyerpdflink;
    }

    public void setFruitionflyerpdflink(String fruitionflyerpdflink) {
        this.fruitionflyerpdflink = fruitionflyerpdflink;
    }

    public String getFruitionflyerpdfimageurl() {
        return fruitionflyerpdfimageurl;
    }

    public void setFruitionflyerpdfimageurl(String fruitionflyerpdfimageurl) {
        this.fruitionflyerpdfimageurl = fruitionflyerpdfimageurl;
    }

    public String getConditionsdescription() {
        return conditionsdescription;
    }

    public void setConditionsdescription(String conditionsdescription) {
        this.conditionsdescription = conditionsdescription;
    }

    public String getConditionsvaliditydescription() {
        return conditionsvaliditydescription;
    }

    public void setConditionsvaliditydescription(String conditionsvaliditydescription) {
        this.conditionsvaliditydescription = conditionsvaliditydescription;
    }
}
