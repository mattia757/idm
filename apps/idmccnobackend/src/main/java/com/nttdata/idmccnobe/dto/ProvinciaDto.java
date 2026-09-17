package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author DelorenziVa
 */
@Component
@Scope("session")
public class ProvinciaDto implements Serializable {

    private static final long serialVersionUID = -6575421442288080083L;

    private String idProvince;
    private String name;
    private String code;

    public ProvinciaDto() {
    }

    public ProvinciaDto(String idProvince, String name, String code) {
        this.idProvince = idProvince;
        this.name = name;
        this.code = code;
    }

    public String getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(String idProvince) {
        this.idProvince = idProvince;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

