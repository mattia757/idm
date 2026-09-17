package com.nttdata.idmccnobe.dto;

import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author DelorenziVa
 */
@Component
@Scope("session")
public class UserSearchResPayloadDto extends CommonPayload{

    private static final long serialVersionUID = -401941021311761581L;
    
    private List<UserDto> users;
    
    public UserSearchResPayloadDto() {}
    
    public UserSearchResPayloadDto(List<UserDto> users) {
        this.users = users;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
    
    
}
