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
public class UserSearchResDto extends HeaderDto implements Serializable {

    private static final long serialVersionUID = 8855588693946900185L;
    
    private UserSearchResPayloadDto payload = new UserSearchResPayloadDto();

    public UserSearchResDto(){}
    
    public UserSearchResDto(CommonDto header){
        this.header = header;
    }
    
    public UserSearchResDto(CommonDto header, UserSearchResPayloadDto payload){
         this.header = header;
         this.payload = payload;
    }
    
    public UserSearchResPayloadDto getPayload() {
        return payload;
    }

    public void setPayload(UserSearchResPayloadDto payload) {
        this.payload = payload;
    }
    
}
