package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nttdata.idmccnobe.enumeration.ErrorCode;
import org.springframework.util.StringUtils;

@JsonPropertyOrder({
    "ack",
    "faultCode",
    "faultString"
})
public class Common implements Serializable
{
    private static final long serialVersionUID = 5440136167177176915L;
    
    public static final String ACK_OK = "OK";
    public static final String ACK_KO = "KO";
    
    private String ack;
    
    private Integer faultCode;
    
    private String faultString;
    
    public Common() {}
    
    public Common(String ack, String faultString) {
        this.ack = ack;
        this.faultString = faultString;
    }
    
    public Common(String ack) {
        this.ack = ack;
    }
    public Common(String ack, ErrorCode errorCode) {
        this.ack = ack;
        this.faultCode = errorCode.errorCode();
        if (!StringUtils.isEmpty(errorCode.errorMessage())) {
            this.faultString = errorCode.errorMessage();
        } else {
            this.faultString = errorCode.toString();
        }
    }
    public Common(String ack, Integer faultCode, String faultString) {
        this.ack = ack;
        this.faultCode = faultCode;
        this.faultString = faultString;
    }
    
    public String getAck() {
        return ack;
    }
    
    public void setAck(String ack) {
        this.ack = ack;
    }
    
    public Integer getFaultCode() {
        return faultCode;
    }
    
    public void setFaultCode(Integer faultCode) {
        this.faultCode = faultCode;
    }
    
    public String getFaultString() {
        return faultString;
    }
    
    public void setFaultString(String faultSting) {
        this.faultString = faultSting;
    }

    @Override
    public String toString() {
        return "Common{" + "ack=" + ack + ", faultCode=" + faultCode + ", faultString=" + faultString + '}';
    }
    
}
