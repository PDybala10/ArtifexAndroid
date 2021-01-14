package com.ndesigne.artifex2.function;

import com.ndesigne.artifex2.model.entities.Arti;

public class Function {

    public static Arti splitme(String data){
        String[] temp = data.split(",");
        Arti newaArti = new Arti();
        newaArti.setMarque(temp[0]);
        newaArti.setModele(temp[1]);
        newaArti.setEnergieNGC(temp[2]);
        newaArti.setImmat(temp[3]);

        return newaArti;
    }

}
