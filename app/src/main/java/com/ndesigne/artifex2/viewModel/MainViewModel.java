package com.ndesigne.artifex2.viewModel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ndesigne.artifex2.model.entities.Arti;
import com.ndesigne.artifex2.model.entities.Image;

public class MainViewModel extends ViewModel {

    public static MutableLiveData<Arti> mutableArti = new MutableLiveData<>();
    public static MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
    public static MutableLiveData<String> mutableLiveDecrypt = new MutableLiveData<>();
    public static MutableLiveData<Image> mutableLiveImage = new MutableLiveData<>();


    public void init(){

        mutableArti.setValue(new Arti("twingo","0000","pire","pousière","dreamTe","data"));

        mutableLiveImage.setValue(new Image("dreamTe",null,"picture"));

    }

}
