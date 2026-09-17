/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author BernardisMa
 */
public class UpdateCashbackPdvRequest implements Serializable{

    private static final long serialVersionUID = 2820423712786477440L;
    
    private String cashbackId;
    private String allSelectedNovacoop;
    private String selectedPdvIdsNovacoop;
    private String allSelectedLiguria;
    private String selectedPdvIdsLiguria;
    private String allSelectedLombardia;
    private String selectedPdvIdsLombardia;

    public String getCashbackId() {
        return cashbackId;
    }

    public void setCashbackId(String cashbackId) {
        this.cashbackId = cashbackId;
    }

    public String getAllSelectedNovacoop() {
        return allSelectedNovacoop;
    }

    public void setAllSelectedNovacoop(String allSelectedNovacoop) {
        this.allSelectedNovacoop = allSelectedNovacoop;
    }

    public String getSelectedPdvIdsNovacoop() {
        return selectedPdvIdsNovacoop;
    }

    public void setSelectedPdvIdsNovacoop(String selectedPdvIdsNovacoop) {
        this.selectedPdvIdsNovacoop = selectedPdvIdsNovacoop;
    }

    public String getAllSelectedLiguria() {
        return allSelectedLiguria;
    }

    public void setAllSelectedLiguria(String allSelectedLiguria) {
        this.allSelectedLiguria = allSelectedLiguria;
    }

    public String getSelectedPdvIdsLiguria() {
        return selectedPdvIdsLiguria;
    }

    public void setSelectedPdvIdsLiguria(String selectedPdvIdsLiguria) {
        this.selectedPdvIdsLiguria = selectedPdvIdsLiguria;
    }

    public String getAllSelectedLombardia() {
        return allSelectedLombardia;
    }

    public void setAllSelectedLombardia(String allSelectedLombardia) {
        this.allSelectedLombardia = allSelectedLombardia;
    }

    public String getSelectedPdvIdsLombardia() {
        return selectedPdvIdsLombardia;
    }

    public void setSelectedPdvIdsLombardia(String selectedPdvIdsLombardia) {
        this.selectedPdvIdsLombardia = selectedPdvIdsLombardia;
    }
    
}
