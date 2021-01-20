package com.ndesigne.artifex2.model.threads;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ndesigne.artifex2.function.Function;
import com.ndesigne.artifex2.model.AES.AES_GCM_Init;
import com.ndesigne.artifex2.model.AES.Decrypt;
import com.ndesigne.artifex2.model.AES.Encrypt;
import com.ndesigne.artifex2.model.entities.Arti;
import com.ndesigne.artifex2.model.entities.Payload;
import com.ndesigne.artifex2.view.MainActivity;
import com.ndesigne.artifex2.viewModel.MainViewModel;

import java.lang.reflect.Type;
import java.util.Base64;

import javax.inject.Inject;

import retrofit2.Response;


public class ThreadsDecrypt extends AppCompatActivity {

    private  volatile boolean stopThread = false;



    public void startThread(AES_GCM_Init aes, Response<Payload> response) {
        stopThread = false;
        ExampleRunnable runnable = new ExampleRunnable(response, aes);
        new Thread(runnable).start();

        //return response.body().getData();
    }
    public  void stopThread() {
        stopThread = true;
    }

    class ExampleRunnable implements Runnable {
        Response<Payload> response;
        AES_GCM_Init aes;
        Type type;
        ExampleRunnable(Response<Payload> response, AES_GCM_Init aes) {
            this.response = response;
            this.aes = aes;

        }
        @Override
        public void run() {
            int i = 0;
            //for (int i = 0; i < seconds; i++) {
            if (stopThread)
                return;

            runOnUiThread(new Runnable() {
                @SuppressLint("NewApi")
                @Override
                public void run() {
                    try {

                        response.body().setData(Decrypt.decrypt(response.body().getData()));
                        MainViewModel.mutableLiveDecrypt.setValue(response.body().getData());


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            System.out.println("\n\n\n\n\n\n\n startThread: " +i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // i++;
            // }
        }
    }

}