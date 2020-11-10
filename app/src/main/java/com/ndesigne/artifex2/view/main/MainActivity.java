package com.ndesigne.artifex2.view.main;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ndesigne.artifex2.AES_GCM_Init;
import com.ndesigne.artifex2.viewModel.remote.APIinterface;
import com.ndesigne.artifex2.model.entities.Arti;
import com.ndesigne.artifex2.Decrypt;
import com.ndesigne.artifex2.Encrypt;
import com.ndesigne.artifex2.R;

import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static String base_url = "https://cb419700-5920-4b57-93e0-17bd8d354702.mock.pstmn.io/";
    private TextView mtextView;
    private Button button;
    Arti arti;
    AES_GCM_Init aes;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtextView = findViewById(R.id.textView);
        button = findViewById(R.id.buttonPost);
        aes = AES_GCM_Init.getInstance();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCall();
            }
        });


    }


    @SuppressLint("NewApi")
    private void apiCall() {

        Gson gson = new GsonBuilder().setLenient().create();
        try {
            arti = new Arti(Base64.getEncoder().encodeToString(aes.getKey()) ,Base64.getEncoder().encodeToString(aes.getIV()) , Encrypt.encrypt("Bonjour tous le monde".getBytes(), aes.getKey(), aes.getIV()));
        } catch (Exception e) {
           System.out.println("je ne suis pas bon");
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create(gson)).build();
        APIinterface apIinterface = retrofit.create(APIinterface.class);

        Call<Arti> call = apIinterface.sendData(arti);
        call.enqueue(new Callback<Arti>() {
            @Override
            public void onResponse(Call<Arti> call, Response<Arti> response) {
                try {
                    mtextView.setText(Decrypt.decrypt(response.body().getMessage(),Base64.getDecoder().decode(response.body().getKey()),Base64.getDecoder().decode(response.body().getIV())));
                } catch (Exception e) {
                    mtextView.setText("mauvais");
                }
                System.out.println("TEST OK");

                Toast.makeText(getApplicationContext(), "API SUCCESSSSSSSSSSSSSSSSSSSS", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Arti> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "noooooooooooooooooooooo", Toast.LENGTH_LONG).show();
            }
        });

    }
}