package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author DelorenziVa
 */
public class UserBeSearchResPayloadDto extends CommonPayload implements Serializable {

    private static final long serialVersionUID = 2654674348367968936L;
    
    public String userProfileUsername;

    public List<UserBeDto> beUsers;

    public UserBeSearchResPayloadDto(List<UserBeDto> beUsers) {
        this.beUsers = beUsers;
    }

    public UserBeSearchResPayloadDto() {}

    public List<UserBeDto> getBeUsers() {
        return beUsers;
    }

    public void setBeUsers(List<UserBeDto> beUsers) {
        this.beUsers = beUsers;
    }
    
    public String getUserProfileUsername() {
        return userProfileUsername;
    }

    public void setUserProfileUsername(String userProfileUsername) {
        this.userProfileUsername = userProfileUsername;
    }
    
}
