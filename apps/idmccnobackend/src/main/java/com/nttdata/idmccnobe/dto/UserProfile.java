package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author smartinenghi
 */
@Component
@Scope("session")
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 5583337149394821652L;
    
    private String username;
    private Integer idCooperativa;
    private String nomeCooperativa;
    private String role;
    private String customerCancellation;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIdCooperativa() {
        return idCooperativa;
    }

    public void setIdCooperativa(Integer idCooperativa) {
        this.idCooperativa = idCooperativa;
    }

    public String getNomeCooperativa() {
        return nomeCooperativa;
    }

    public void setNomeCooperativa(String nomeCooperativa) {
        this.nomeCooperativa = nomeCooperativa;
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
