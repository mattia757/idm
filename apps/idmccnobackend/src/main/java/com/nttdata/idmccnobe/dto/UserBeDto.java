package com.nttdata.idmccnobe.dto;

import com.nttdata.idmccnobe.util.Constants;
import java.io.Serializable;

/**
 *
 * @author DelorenziVa
 */
public class UserBeDto implements Serializable {

    private static final long serialVersionUID = 7570081134162246533L;
    
    private String id;
    private String username;
    private String coop;
    private String role;
    private String customerCancellation;

    public UserBeDto(String id, String username, String coop, String role) {
        this.id = id;
        this.username = username;
        this.coop = coop;
        this.role = role;
    }

    public UserBeDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
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
        if (role.equalsIgnoreCase("ADMIN")) {
            this.role = Constants.USER_ROLE_ADMIN;
        } else if (role.equalsIgnoreCase("CCNO")) {
            this.role = Constants.USER_ROLE_CCNO;
        } else if (role.equalsIgnoreCase("MODIFY")) {
            this.role = Constants.USER_ROLE_MODIFY;
        } else if(role.equalsIgnoreCase("VIEW")) {
            this.role = Constants.USER_ROLE_VIEW;
        }
    }

    public String getCustomerCancellation() {
        return customerCancellation;
    }

    public void setCustomerCancellation(String customerCancellation) {
        if (customerCancellation.equals("0")) {
            this.customerCancellation = "NO";
        } else if (customerCancellation.equals("1")) {
            this.customerCancellation = "SI";
        } else {
            this.customerCancellation = "ND";
        }
    }
    
    
}
