package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author DelorenziVa
 */
public class RemoveCardHeaderResDto implements Serializable {

    private static final long serialVersionUID = -9127834469338257090L;
    
    protected CommonDto header = new CommonDto();

    public CommonDto getHeader() {
        return header;
    }

    public void setHeader(CommonDto header) {
        this.header = header;
    }

    public RemoveCardHeaderResDto() {
    }
}
