package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

public class ExportUserCancelledExcelRequest implements Serializable {

    private static long serialVersionUID = -2073995086059727347L;
    
    private String coopId;
    private String email;

    public ExportUserCancelledExcelRequest() {
    }

    public String getCoopId() {
        return coopId;
    }

    public void setCoopId(String coopId) {
        this.coopId = coopId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "coopId="+getCoopId()+", email="+getEmail(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
