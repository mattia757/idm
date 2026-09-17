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
public class PrivaciesDto extends HeaderDto implements Serializable{

    private static final long serialVersionUID = -7503965312861025154L;
    
    private PrivacyPayload payload = new PrivacyPayload();
    
    public PrivaciesDto() {}
    
    public PrivaciesDto(CommonDto header, PrivacyPayload payload) {
        this.header = header;
        this.payload = payload;
    }

    public PrivacyPayload getPayload() {
        return payload;
    }

    public void setPayload(PrivacyPayload payload) {
        this.payload = payload;
    }
    
}
