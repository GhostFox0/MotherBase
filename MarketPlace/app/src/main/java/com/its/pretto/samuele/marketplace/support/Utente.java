package com.its.pretto.samuele.marketplace.support;

/**
 * Created by Samuele.Pretto on 22/12/2016.
 */

public class Utente {

    private String mail, pass, name, surn, address, c_pubblica, c_simmetrica;


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurn() {
        return surn;
    }

    public void setSurn(String surn) {
        this.surn = surn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getC_pubblica() {
        return c_pubblica;
    }

    public void setC_pubblica(String c_pubblica) {
        this.c_pubblica = c_pubblica;
    }

    public String getC_simmetrica() {
        return c_simmetrica;
    }

    public void setC_simmetrica(String c_simmetrica) {
        this.c_simmetrica = c_simmetrica;
    }

    public Utente() {
    }

    public Utente(String mail, String pass, String name, String surn, String address) {
        this.mail = mail;
        this.pass = pass;
        this.name = name;
        this.surn = surn;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "mail='" + mail + '\'' +
                ", pass='" + pass + '\'' +
                ", name='" + name + '\'' +
                ", surn='" + surn + '\'' +
                ", address='" + address + '\'' +
                ", c_pubblica='" + c_pubblica + '\'' +
                ", c_simmetrica='" + c_simmetrica + '\'' +
                '}';
    }
}
