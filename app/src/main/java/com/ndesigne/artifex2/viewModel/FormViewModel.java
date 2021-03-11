package com.ndesigne.artifex2.viewModel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ndesigne.artifex2.model.entities.Arti;

public class FormViewModel extends ViewModel {

    public static MutableLiveData<Arti> mutableArtiForm = new MutableLiveData<>();


    public void init() {

        mutableArtiForm.setValue(new Arti("data"));


    }

}
