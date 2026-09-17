package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author DelorenziVa
 */
public class AddCardHeaderResDto implements Serializable {

    private static final long serialVersionUID = 6272939550584909337L;
    
    protected CommonDto header = new CommonDto();

    public CommonDto getHeader() {
        return header;
    }

    public void setHeader(CommonDto header) {
        this.header = header;
    }

    public AddCardHeaderResDto() {
    }
}
