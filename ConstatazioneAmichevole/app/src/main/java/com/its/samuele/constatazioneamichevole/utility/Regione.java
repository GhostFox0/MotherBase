package com.its.samuele.constatazioneamichevole.utility;

import java.util.ArrayList;

/**
 * Created by Utente on 27/09/2016.
 */

public class Regione {
    private String nomeRegione;
    private ArrayList<Provincia> provinceArrayList;

    public Regione() {
        provinceArrayList = new ArrayList<>();
    }

    public String getNomeRegione() {
        return nomeRegione;
    }

    public void setNomeRegione(String nomeRegione) {
        this.nomeRegione = nomeRegione;
    }

    public ArrayList<Provincia> getProvinceArrayList() {
        return provinceArrayList;
    }

    public void setProvinceArrayList(ArrayList<Provincia> provinceArrayList) {
        this.provinceArrayList = provinceArrayList;
    }
}
