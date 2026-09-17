package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

public class SimpleResult implements Serializable
{
    private static final long serialVersionUID = -8465496780588988512L;

    protected Common common = new Common();
    
    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    @Override
    public String toString() {
        return "SimpleResult{" + "common=" + common + '}';
    }
    
}
