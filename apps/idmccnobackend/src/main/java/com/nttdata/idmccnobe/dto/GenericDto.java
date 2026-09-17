package com.nttdata.idmccnobe.dto;
import java.io.Serializable;
/**
 *
 * @author DelorenziVa
 */
public class GenericDto extends HeaderDto implements Serializable {

    private static final long serialVersionUID = 5478943452266518585L;

    private CommonPayload payload = new CommonPayload();

    public GenericDto() {
    }

    public GenericDto(CommonPayload payload) {
        this.payload = payload;
    }

    public CommonPayload getPayload() {
        return payload;
    }

    public void setPayload(CommonPayload payload) {
        this.payload = payload;
    }   
}
