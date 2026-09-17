package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author DelorenziVa
 */
public class UserBeRequest implements Serializable{

    private static final long serialVersionUID = -4651542702166390541L;
    
    private String username;
    private String coop;
    private String role;
    private String customerCancellation;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCoop() {
        return coop;
    }

    public void setCoop(String coop) {
        this.coop = coop;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCustomerCancellation() {
        return customerCancellation;
    }

    public void setCustomerCancellation(String customerCancellation) {
        this.customerCancellation = customerCancellation;
    }
    
}
