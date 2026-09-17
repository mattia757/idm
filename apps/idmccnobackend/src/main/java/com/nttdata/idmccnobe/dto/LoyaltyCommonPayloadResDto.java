package com.nttdata.idmccnobe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoyaltyCommonPayloadResDto implements Serializable {

    private static final long serialVersionUID = 4826658764777971516L;

    private String message;

    public LoyaltyCommonPayloadResDto() {
    }

    public LoyaltyCommonPayloadResDto(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    

}