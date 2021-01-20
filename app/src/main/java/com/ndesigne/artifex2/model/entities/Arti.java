package com.ndesigne.artifex2.model.entities;

import android.graphics.Bitmap;

public class Arti extends BasePayload{

    private String marque, modele, energieNGC, immat, owner;

    public Arti( String marque, String modele, String energieNGC, String immat, String owner, String key ) {
        super(key);

        this.marque = marque;
        this.modele = modele;
        this.energieNGC = energieNGC;
        this.immat = immat;
        this.owner = owner;

    }

    public Arti(String key){
        super(key);
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String concat(){
        return getMarque()+","+getModele()+","+getEnergieNGC()+","+getImmat();
    }
}
