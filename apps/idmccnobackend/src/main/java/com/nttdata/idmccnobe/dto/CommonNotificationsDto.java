/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author FerrandicoSi
 */

@JsonPropertyOrder({
    "returnCode",
    "returnMessage",
    "life",
    "timestamp",
    "statement",
    "jarvis",
    "version"
})

@Component
@Scope("session")
public class CommonNotificationsDto {
    
    private Integer returnCode;
    private String returnMessage;
    private Integer life;
    private String timestamp;
    private String statement;
    private String jarvis;
    private String version;

    public CommonNotificationsDto() {
    }

    public CommonNotificationsDto(Integer returnCode, String returnMessage, Integer life, String timestamp, String statement, String jarvis, String version) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
        this.life = life;
        this.timestamp = timestamp;
        this.statement = statement;
        this.jarvis = jarvis;
        this.version = version;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getJarvis() {
        return jarvis;
    }

    public void setJarvis(String jarvis) {
        this.jarvis = jarvis;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return 
            "returnCode=" + returnCode + ", " +
            "returnMessage=" + returnMessage+ ", " +
            "life=" + life+ ", " +
            "timestamp=" + timestamp+ ", " +
            "statement=" + statement+ ", " +
            "jarvis=" + jarvis+ ", " +
            "version=" + version;
    }
    
}
