package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author DelorenziVa
 */
public class RemoveCardResDto extends RemoveCardHeaderResDto implements Serializable{

    private static final long serialVersionUID = -8597240279739202485L;
    
    private CommonPayload payload = new CommonPayload();

    public CommonPayload getPayload() {
        return payload;
    }

    public void setPayload(CommonPayload payload) {
        this.payload = payload;
    }

}
