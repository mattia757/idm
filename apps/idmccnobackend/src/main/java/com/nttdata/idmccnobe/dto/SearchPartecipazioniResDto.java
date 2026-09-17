package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

public class SearchPartecipazioniResDto extends HeaderDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private SearchPartecipazioniPayloadDto payload = new SearchPartecipazioniPayloadDto();

    public SearchPartecipazioniPayloadDto getPayload() {
        return payload;
    }

    public void setPayload(SearchPartecipazioniPayloadDto payload) {
        this.payload = payload;
    }
}
