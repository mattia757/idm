package com.nttdata.idmccnobe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author DelorenziVa
 */
@Component
@Scope("session")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonPayload implements Serializable {

    private static final long serialVersionUID = 1287944362585032615L;
    
    private String message;

    public CommonPayload() {
    }

    public CommonPayload(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
