package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author DelorenziVa
 */
public class VerifyLoyaltyResDto extends LoyaltyHeaderResDto implements Serializable{

    private static final long serialVersionUID = 1058199206171968293L;

    public VerifyLoyaltyResDto() {
    }
    
    LoyaltyPayloadResDto payload = new LoyaltyPayloadResDto();

    
    public VerifyLoyaltyResDto(LoyaltyPayloadResDto payload) {
        this.payload = payload;
    }

    public LoyaltyPayloadResDto getPayload() {
        return payload;
    }

    public void setPayload(LoyaltyPayloadResDto payload) {
        this.payload = payload;
    }
    
}
