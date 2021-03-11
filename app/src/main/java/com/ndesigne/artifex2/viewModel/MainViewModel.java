package com.ndesigne.artifex2.viewModel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ndesigne.artifex2.model.entities.Arti;
import com.ndesigne.artifex2.model.entities.Image;
import com.ndesigne.artifex2.view.Form;
import com.ndesigne.artifex2.view.MainActivity;

public class MainViewModel extends ViewModel {

    public static MutableLiveData<Arti> mutableArti = new MutableLiveData<>();
    public static MutableLiveData<String> mutableLiveData;
    public static MutableLiveData<String> mutableLiveDecrypt;
    public static MutableLiveData<Image> mutableLiveImage = new MutableLiveData<>();


    public static void init() {

        mutableArti.setValue(
                new Arti(0, 0, Form.setMarqueText.getText().toString(), Form.setModeleText.getText().toString(),
                        Form.setVersion_com.getText().toString(), Form.setEnergie.getText().toString(), Form.setTransmission.getText().toString(),
                        Form.setCategorie.getText().toString(), Form.setDescription.getText().toString(),
                        Integer.valueOf(Form.setAnnee.getText().toString()), Integer.valueOf(Form.setAn_mise_en_circulation.getText().toString()),
                        Double.valueOf(Form.setKilometrage.getText().toString()), Double.valueOf(Form.setPrix.getText().toString()),
                        Boolean.valueOf(Form.setPrem_main.toString()), Form.setCarroserie.getText().toString(), "dreamTe", "data"));


        mutableLiveImage.setValue(new Image(MainActivity.clientIdGlobal, 0, "dreamTe", null, "picture"));
        mutableLiveData = new MutableLiveData<>();
        mutableLiveDecrypt = new MutableLiveData<>();

    }

}
