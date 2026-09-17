package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author DelorenziVa
 */
public class CreateUserBeRequest implements Serializable {

    private static final long serialVersionUID = 1783290867042365111L;
    
    private String username;
    private String password;
    private String repeatPassword;
    private String role;
    private String coop;
    private String customerCancellation;

    public CreateUserBeRequest() {
    }

    public CreateUserBeRequest(String username, String password, String repeatPassword, String role, String coop, String customerCancellation) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.role = role;
        this.coop = coop;
        this.customerCancellation = customerCancellation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCoop() {
        return coop;
    }

    public void setCoop(String coop) {
        this.coop = coop;
    }

    public String getCustomerCancellation() {
        return customerCancellation;
    }

    public void setCustomerCancellation(String customerCancellation) {
        this.customerCancellation = customerCancellation;
    }
    
}
