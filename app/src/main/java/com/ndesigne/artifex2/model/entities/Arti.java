package com.ndesigne.artifex2.model.entities;

public class Arti {

    private String marque, modele, energieNGC, immat;

    public Arti( String marque, String modele, String energieNGC, String immat ) {

        this.marque = marque;
        this.modele = modele;
        this.energieNGC = energieNGC;
        this.immat = immat;

    }

    public Arti(){

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



    public String concat(){
        return getMarque()+","+getModele()+","+getEnergieNGC()+","+getImmat();
    }
}
