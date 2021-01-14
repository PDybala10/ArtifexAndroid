package com.ndesigne.artifex2.function;

import com.ndesigne.artifex2.model.entities.Arti;

public class Function {

    public static Arti splitme(String data){
        String[] temp = data.split(", ");
        Arti newaArti = new Arti();
        for(int i=0; i<temp.length; i++){
            newaArti.setCarid(Integer.parseInt(temp[0]));
            newaArti.setClientid(Integer.parseInt(temp[1]));
            newaArti.setEnergieNGC(temp[2]);
            newaArti.setImmat(temp[3]);
            newaArti.setIV(temp[4]);
            newaArti.setKey(temp[5]);
            newaArti.setMarque(temp[6]);
            newaArti.setModele(temp[7]);
        }
        return newaArti;
    }
}
