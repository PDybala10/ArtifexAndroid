package com.ndesigne.artifex2.function;

import com.ndesigne.artifex2.model.entities.Arti;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Function {

    public static Arti splitme(String data){
        String[] temp = data.split(",");
        Arti newaArti = new Arti("data");
        newaArti.setMarque(temp[0]);
        newaArti.setModele(temp[1]);
        newaArti.setEnergieNGC(temp[2]);
        newaArti.setImmat(temp[3]);

        return newaArti;
    }



}
