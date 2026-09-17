package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchPartecipazioniPayloadDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<PartecipazioneInteresseDto> partecipazioniList = new ArrayList<>();

    public List<PartecipazioneInteresseDto> getPartecipazioniList() {
        return partecipazioniList;
    }

    public void setPartecipazioniList(List<PartecipazioneInteresseDto> partecipazioniList) {
        this.partecipazioniList = partecipazioniList;
    }
}
