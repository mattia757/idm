package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author DelorenziVa
 */
public class UserResPrivacyDto implements Serializable{

    private static final long serialVersionUID = 1852749511540194597L;
    
    private String coop;
    private List<UserResPrivacyOptin> optins;

    public String getCoop() {
        return coop;
    }

    public void setCoop(String coop) {
        this.coop = coop;
    }

    public List<UserResPrivacyOptin> getOptins() {
        return optins;
    }

    public void setOptins(List<UserResPrivacyOptin> optins) {
        this.optins = optins;
    }
    
    
    
}
