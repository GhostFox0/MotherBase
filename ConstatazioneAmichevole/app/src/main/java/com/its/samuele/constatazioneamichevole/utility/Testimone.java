package com.its.samuele.constatazioneamichevole.utility;

/**
 * Created by Samuele.Pretto on 27/09/2016.
 */

public class Testimone {

    private String cognome;
    private String nome;
    private String indirizzo;
    private String telefono;
    private String Locazione;


    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getLocazione() {
        return Locazione;
    }

    public void setLocazione(String locazione) {
        Locazione = locazione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
