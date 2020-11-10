package com.ndesigne.artifex2.model.entities;

public class Arti {

    private String key,IV,message;
    public Arti(String key, String IV, String message){
        this.key = key;
        this.IV = IV;
        this.message = message;
    }



    public String getKey() {
        return key;
    }

    public String getIV() {
        return IV;
    }

    public String getMessage() {
        return message;
    }



    public String toString(){
        return "Key :"+getKey() + "\nIV :"+getIV() + "\nMessage : " +getMessage();
    }
}
