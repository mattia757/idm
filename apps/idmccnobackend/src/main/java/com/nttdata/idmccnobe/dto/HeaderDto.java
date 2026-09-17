package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author DelorenziVa
 */
public class HeaderDto implements Serializable {

    private static final long serialVersionUID = 3018325710928466212L;

    protected CommonDto header = new CommonDto();

    public CommonDto getHeader() {
        return header;
    }

    public void setHeader(CommonDto header) {
        this.header = header;
    }

    public HeaderDto() {
    }
     
}
