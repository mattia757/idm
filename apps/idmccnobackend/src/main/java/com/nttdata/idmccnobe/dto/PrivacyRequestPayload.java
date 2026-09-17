
package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author DelorenziVa
 */
@Component
@Scope("session")
public class PrivacyRequestPayload implements Serializable{

    private static final long serialVersionUID = 7081308248645999601L;
    
    private String coopId;
    private String application;
    private String userType;

    public String getCoopId() {
        return coopId;
    }

    public void setCoopId(String coopId) {
        this.coopId = coopId;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
}
