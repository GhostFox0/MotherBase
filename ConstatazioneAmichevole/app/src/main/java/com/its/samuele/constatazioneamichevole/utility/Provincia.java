package com.its.samuele.constatazioneamichevole.utility;

import java.util.ArrayList;

/**
 * Created by Utente on 27/09/2016.
 */

public class Provincia {
    private String nomeProvincia, codeProvincia;
    private ArrayList<Comune> comuniArrayList;

    public Provincia() {
        comuniArrayList = new ArrayList<>();
    }

    public String getNomeProvincia() {
        return nomeProvincia;
    }

    public void setNomeProvincia(String nomeProvincia) {
        this.nomeProvincia = nomeProvincia;
    }

    public String getCodeProvincia() {
        return codeProvincia;
    }

    public void setCodeProvincia(String codeProvincia) {
        this.codeProvincia = codeProvincia;
    }

    public ArrayList<Comune> getComuniArrayList() {
        return comuniArrayList;
    }

    public void setComuniArrayList(ArrayList<Comune> comuniArrayList) {
        this.comuniArrayList = comuniArrayList;
    }
}
