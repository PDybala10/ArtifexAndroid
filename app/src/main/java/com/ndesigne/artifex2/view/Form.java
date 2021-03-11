package com.ndesigne.artifex2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.ndesigne.artifex2.R;
import com.ndesigne.artifex2.model.entities.Arti;
import com.ndesigne.artifex2.viewModel.FormViewModel;

import javax.inject.Inject;


public class Form extends AppCompatActivity {

    public static TextView setMarqueText, setModeleText, setVersion_com, setEnergie, setTransmission, setCategorie, setDescription, setAnnee, setCarroserie;
    public static TextView setAn_mise_en_circulation, setKilometrage, setPrix, setPrem_main;
    public static Button button;
    @Inject
    FormViewModel formViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        setMarqueText = findViewById(R.id.setMarqueText);
        setModeleText = findViewById(R.id.setModeleText);
        setVersion_com = findViewById(R.id.setVersion);
        setEnergie = findViewById(R.id.setEnergie);
        setTransmission = findViewById(R.id.setTransmission);
        setCategorie = findViewById(R.id.setCategorie);
        setCarroserie = findViewById(R.id.setCarosserie);
        setDescription = findViewById(R.id.setDescription);
        setAnnee = findViewById(R.id.setAnnee);
        setAn_mise_en_circulation = findViewById(R.id.setAnneMiseCircu);
        setKilometrage = findViewById(R.id.setKilometrage);
        setPrix = findViewById(R.id.setPrix);
        setPrem_main = findViewById(R.id.setPremierMain);
        button = findViewById(R.id.buttonPost);


        FormViewModel.mutableArtiForm.observe(this, new Observer<Arti>() {
            @Override
            public void onChanged(Arti arti) {

                arti.setModele(setModeleText.getText().toString());
                arti.setMarque(setMarqueText.getText().toString());
                arti.setVersion_com(setVersion_com.getText().toString());
                arti.setEnergie(setEnergie.getText().toString());
                arti.setTransmission(setTransmission.getText().toString());
                arti.setCategorie(setCategorie.getText().toString());
                arti.setCarrosserie(setCarroserie.getText().toString());
                arti.setDescription(setDescription.getText().toString());
                arti.setAnnee(Integer.valueOf(setAnnee.getText().toString()));
                arti.setAn_mise_en_circulation(Integer.valueOf(setAn_mise_en_circulation.getText().toString()));
                arti.setKilometrage(Double.valueOf(setKilometrage.getText().toString()));
                arti.setPrix(Double.valueOf(setPrix.getText().toString()));
                arti.setPrem_main(setPrem_main.getText().toString().equals("true"));
                arti.setOwner("dreamTe");

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}