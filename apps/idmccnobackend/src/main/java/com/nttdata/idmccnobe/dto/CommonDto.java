package com.nttdata.idmccnobe.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MartinenghiSt
 */
@JsonPropertyOrder({
    "result",
    "errorCode",
    "errorMessage"
})

@Component
@Scope("session")
public class CommonDto implements Serializable {
    
    public static final String ACK_OK = "OK";
    public static final String ACK_KO = "KO";

    private String result;
    private Integer errorCode;
    private String errorMessage;

    public CommonDto() {
    }

    public CommonDto(String result, Integer errorCode, String errorMessage) {
        this.result = result;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
