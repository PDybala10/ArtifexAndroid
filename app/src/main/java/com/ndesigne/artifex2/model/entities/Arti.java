package com.ndesigne.artifex2.model.entities;

public class Arti extends BasePayload {

    private String marque, modele, version_com, energie, transmission, categorie, description, owner, carrosserie;
    private int annee, an_mise_en_circulation, voitureId, clientId;
    private double kilometrage, prix;
    private boolean prem_main;

    public Arti(int voitureId, int clientId, String marque, String modele, String version_com, String energie, String transmission, String categorie, String description, int annee, int an_mise_en_circulation, double kilometrage, double prix, boolean prem_main, String carrosserie, String owner, String key) {
        super(key);
        this.voitureId = voitureId;
        this.clientId = clientId;
        this.marque = marque;
        this.modele = modele;
        this.version_com = version_com;
        this.energie = energie;
        this.transmission = transmission;
        this.categorie = categorie;
        this.description = description;
        this.owner = owner;
        this.annee = annee;
        this.an_mise_en_circulation = an_mise_en_circulation;
        this.kilometrage = kilometrage;
        this.prix = prix;
        this.prem_main = prem_main;
        this.carrosserie = carrosserie;
    }


    public Arti(String key) {
        super(key);
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getVersion_com() {
        return version_com;
    }

    public void setVersion_com(String version_com) {
        this.version_com = version_com;
    }

    public String getEnergie() {
        return energie;
    }

    public void setEnergie(String energie) {
        this.energie = energie;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getAn_mise_en_circulation() {
        return an_mise_en_circulation;
    }

    public void setAn_mise_en_circulation(int an_mise_en_circulation) {
        this.an_mise_en_circulation = an_mise_en_circulation;
    }

    public double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public boolean isPrem_main() {
        return prem_main;
    }

    public void setPrem_main(boolean prem_main) {
        this.prem_main = prem_main;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCarrosserie() {
        return carrosserie;
    }

    public void setCarrosserie(String carrosserie) {
        this.carrosserie = carrosserie;
    }

    public int getVoitureId() {
        return voitureId;
    }

    public void setVoitureId(int voitureId) {
        this.voitureId = voitureId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /* public String concat(){
        return getMarque()+","+getModele()+","+getEnergieNGC()+","+getImmat();
    }*/
}
