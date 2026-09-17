
package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author DelorenziVa
 */
public class UserRequest implements Serializable{

    private static final long serialVersionUID = 2820423712786477440L;
    
    private String userId;
    private String name;
    private String surname;
    private String email;
    private String eanCard;
    private String coopId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEanCard() {
        return eanCard;
    }

    public void setEanCard(String eanCard) {
        this.eanCard = eanCard;
    }

    public String getCoopId() {
        return coopId;
    }

    public void setCoopId(String coopId) {
        this.coopId = coopId;
    }
    
}
