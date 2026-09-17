
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
public class UserBeResDto extends HeaderDto implements Serializable {

    private static final long serialVersionUID = -4957313396988117913L;
    
    private UserBeSearchResPayloadDto payload = new UserBeSearchResPayloadDto();

    public UserBeResDto() {
    }
    
    public UserBeResDto(CommonDto header){
        this.header = header;
    }
    
    public UserBeResDto(CommonDto header, UserBeSearchResPayloadDto payload){
        this.header = header;
        this.payload = payload;
    }
    
    public UserBeSearchResPayloadDto getPayload() {
        return payload;
    }

    public void setPayload(UserBeSearchResPayloadDto payload) {
        this.payload = payload;
    }
    
}
