package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author DelorenziVa
 */
public class VerifyLoyaltyReqDto implements Serializable{

    private static final long serialVersionUID = -3638367306451931847L;
    
    private String eanCard;
    private String birthDate;
    private String application;
    private String coopId;

    public String getEanCard() {
        return eanCard;
    }

    public void setEanCard(String eanCard) {
        this.eanCard = eanCard;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getCoopId() {
        return coopId;
    }

    public void setCoopId(String coopId) {
        this.coopId = coopId;
    }
    
    
}
