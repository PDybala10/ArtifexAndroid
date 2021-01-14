package com.ndesigne.artifex2.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.google.gson.Gson;
import com.ndesigne.artifex2.R;
import com.ndesigne.artifex2.function.Function;
import com.ndesigne.artifex2.injection.ArtiApplication;
import com.ndesigne.artifex2.model.AES.AES_GCM_Init;
import com.ndesigne.artifex2.model.AES.Decrypt;
import com.ndesigne.artifex2.model.AES.Encrypt;
import com.ndesigne.artifex2.model.entities.Arti;
import com.ndesigne.artifex2.model.entities.Payload;
import com.ndesigne.artifex2.model.threads.Threads;
import com.ndesigne.artifex2.model.threads.ThreadsDecrypt;
import com.ndesigne.artifex2.viewModel.APIinterface;
import com.ndesigne.artifex2.viewModel.MainViewModel;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    @Inject
    Gson gson;

    @Inject
    APIinterface apIinterface;

    @Inject
    MainViewModel mainViewModel;

    @Inject
    String base_url;

   // public static String base_url = "https://cb419700-5920-4b57-93e0-17bd8d354702.mock.pstmn.io/";
    public static TextView clientidText,marqueText,modeleText,energieText,immatText;
    private Button button;
    public static Arti arti = new Arti();
    AES_GCM_Init aes;
    Threads threads;
    ThreadsDecrypt threadsDecrypt;
    ArrayList<String> carList;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        carList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        immatText = findViewById(R.id.immatText);
        clientidText = findViewById(R.id.clientidText);
        marqueText = findViewById(R.id.marqueText);
        modeleText = findViewById(R.id.modeleText);
        energieText = findViewById(R.id.energieText);
        button = findViewById(R.id.buttonPost);
        aes = AES_GCM_Init.getInstance();
        threads = new Threads();
        threadsDecrypt = new ThreadsDecrypt();

       // carList.add(0,clientidText.getText().toString());
        carList.add(marqueText.getText().toString());
        carList.add(modeleText.getText().toString());
        carList.add(energieText.getText().toString());
        carList.add(immatText.getText().toString());

        ((ArtiApplication) getApplication()).getNetworkComponent().inject(MainActivity.this);

        mainViewModel.immatText.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                immatText.setText(s);
            }
        });
        mainViewModel.clientidText.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                clientidText.setText(s);
            }
        });
        mainViewModel.marqueText.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                marqueText.setText(s);
            }
        });
        mainViewModel.modeleText.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                modeleText.setText(s);
            }
        });
        mainViewModel.energieText.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                energieText.setText(s);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCall();
            }
        });


    }

    @SuppressLint("NewApi")
    private void apiCall() {
       Payload payload  = new Payload("mBWGa9XY+l+tpD+jz53fHYFEZoqE8fLmM6r8idab7sJkA2Qr4Q8PD+HRyox3QQrE2pIC8pOfxJgOKUEA");
        // gson = new GsonBuilder().setLenient().create();
        try {
         /*   //chiffrement
             threads.startThread(aes, carList);

             //System.out.println(MainActivity.arti);
           String testMess = Encrypt.encrypt("Bonjour tous le monde".getBytes(), aes.getKey(), aes.getIV());
           //System.out.println("Bonjour tous le monde".getBytes().length);
            String testKey = Base64.getEncoder().encodeToString(aes.getKey());
            String testIV = Base64.getEncoder().encodeToString(aes.getIV());
            System.out.println("cipher = "+testMess+" " +"key = "+testKey+" "+"Iv = "+ testIV);*/

          //  arti = new Arti(Base64.getEncoder().encodeToString(aes.getKey()) ,Base64.getEncoder().encodeToString(aes.getIV()) , Encrypt.encrypt("Bonjour tous le monde".getBytes(), aes.getKey(), aes.getIV()));


            //payload.setData(Encrypt.encrypt(payload.getData().getBytes(), aes.getKey(), aes.getIV()));

        } catch (Exception e) {
           System.out.println("je ne suis pas bon");
        }
        //retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create(gson)).build();
       // APIinterface apIinterface = retrofit.create(APIinterface.class);

        Call<Payload> call = apIinterface.sendData(payload);
        call.enqueue(new Callback<Payload>() {
            @Override
            public void onResponse(Call<Payload> call, Response<Payload> response) {
                try {
                   // threadsDecrypt.startThread(aes,response);
                    System.out.println(response.body().getData());
                    System.out.println("je suis la response");
                   // Function.splitme(Base64.getDecoder().decode(response.body().getData()))
                    String resp = Decrypt.decrypt(response.body().getData());
                    System.out.println("je suis la response : "+resp);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("TEST OK");

                Toast.makeText(getApplicationContext(), "API SUCCESSSSSSSSSSSSSSSSSSSS", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Payload> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "noooooooooooooooooooooo", Toast.LENGTH_LONG).show();
            }
        });

           // threads.stopThread();
            //threadsDecrypt.stopThread();
    }


}