package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author DelorenziVa
 */
public class AddCardResDto extends AddCardHeaderResDto implements Serializable{

    private static final long serialVersionUID = -8793554348700291731L;

    private CommonPayload payload = new CommonPayload();

    public CommonPayload getPayload() {
        return payload;
    }

    public void setPayload(CommonPayload payload) {
        this.payload = payload;
    }
    
}
