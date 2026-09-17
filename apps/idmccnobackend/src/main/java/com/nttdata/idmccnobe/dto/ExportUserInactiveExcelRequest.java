package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

public class ExportUserInactiveExcelRequest implements Serializable {

    private static long serialVersionUID = -2073995086059727348L;
    
    private String application;

    public ExportUserInactiveExcelRequest() {
    }

    public ExportUserInactiveExcelRequest(String application) {
        this.application = application;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
    
    @Override
    public String toString() {
        return "application="+application; //To change body of generated methods, choose Tools | Templates.
    }
    
}
