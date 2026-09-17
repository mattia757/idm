package com.nttdata.idmccnobe.dto;

import com.nttdata.idmccnobe.util.Constants;
import java.io.Serializable;

/**
 *
 * @author DelorenziVa
 */
public class UserEanCardDto implements Serializable {

    private static final long serialVersionUID = 7037123479791670322L;
    
    private String eanCard;
    private String coopId;

    public UserEanCardDto(String eanCard, String coopId) {
        this.eanCard = eanCard;
        this.coopId = coopId;
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
