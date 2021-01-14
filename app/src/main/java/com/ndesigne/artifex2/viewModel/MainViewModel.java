package com.ndesigne.artifex2.viewModel;

import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public static MutableLiveData<String> immatText = new MutableLiveData<>();
    public static MutableLiveData<String> clientidText = new MutableLiveData<>();
    public static MutableLiveData<String> marqueText = new MutableLiveData<>();
    public static MutableLiveData<String> modeleText = new MutableLiveData<>();
    public static MutableLiveData<String> energieText = new MutableLiveData<>();

    public void init(){

        immatText.setValue("immat: AA-455-ZE");
        clientidText.setValue("Liste de voiture Pour id: 4");
        marqueText.setValue("Marque: Mercedes");
        modeleText.setValue("Modèle: classe A");
        energieText.setValue("EnergieNGC: Essence");
    }

}
