package com.ndesigne.artifex2.model.entities;

public class Arti {

    private String key,IV,marque, modele, energieNGC, immat;
    private int clientid, carid;

    public Arti(int clientid, int carid, String marque, String modele, String energieNGC, String immat,String key, String IV ) {
        this.key = key;
        this.IV = IV;
        this.marque = marque;
        this.modele = modele;
        this.energieNGC = energieNGC;
        this.immat = immat;
        this.clientid = clientid;
        this.carid = carid;
    }

    public Arti(){

    }

    public String getKey() {
        return key;
    }

    public String getIV() {
        return IV;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public String getEnergieNGC() {
        return energieNGC;
    }

    public String getImmat() {
        return immat;
    }

    public int getClientid() {
        return clientid;
    }

    public int getCarid() {
        return carid;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setIV(String IV) {
        this.IV = IV;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setEnergieNGC(String energieNGC) {
        this.energieNGC = energieNGC;
    }

    public void setImmat(String immat) {
        this.immat = immat;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    public String concat(){
        return getKey()+","+getIV()+","+getMarque()+","+getModele()+","+getEnergieNGC()+","+getImmat()+","+getClientid()+","+getCarid();
    }
}
