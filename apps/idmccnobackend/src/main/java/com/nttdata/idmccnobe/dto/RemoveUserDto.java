package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author FacchettiMa
 */
@Component
@Scope("session")
public class RemoveUserDto extends HeaderDto implements Serializable {

    private static final long serialVersionUID = 7945829722177788522L;

    private CommonPayload payload = new CommonPayload();

    public RemoveUserDto() {
    }

    public RemoveUserDto(CommonPayload payload) {
        this.payload = payload;
    }

    public CommonPayload getPayload() {
        return payload;
    }

    public void setPayload(CommonPayload payload) {
        this.payload = payload;
    }

    

    
}
