package com.ndesigne.artifex2.model.threads;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

import com.ndesigne.artifex2.model.AES.AES_GCM_Init;
import com.ndesigne.artifex2.model.AES.Decrypt;
import com.ndesigne.artifex2.model.AES.Encrypt;
import com.ndesigne.artifex2.model.entities.Arti;
import com.ndesigne.artifex2.view.MainActivity;

import java.util.Base64;

import retrofit2.Response;


public class ThreadsDecrypt extends AppCompatActivity {

   /* private  volatile boolean stopThread = false;

    public void  startThread(AES_GCM_Init aes, Response<Arti> response) {
        stopThread = false;
        ExampleRunnable runnable = new ExampleRunnable(response, aes);
        new Thread(runnable).start();

    }
    public  void stopThread() {
        stopThread = true;
    }

    class ExampleRunnable implements Runnable {
        Response<Arti> response;
        AES_GCM_Init aes;
        ExampleRunnable(Response<Arti> response, AES_GCM_Init aes) {
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
                                System.out.println("je suis la clé "+response.body().getKey());
                                System.out.println("je suis ll'IV "+response.body().getIV());
                                MainActivity.immatText.setText(Decrypt.decrypt(response.body().getImmat(),Base64.getDecoder().decode(response.body().getKey()),Base64.getDecoder().decode(response.body().getIV())));
                                MainActivity.clientidText.setText("Liste de voiture Pour id: "+response.body().getClientid());
                                MainActivity.marqueText.setText(Decrypt.decrypt(response.body().getMarque(),Base64.getDecoder().decode(response.body().getKey()),Base64.getDecoder().decode(response.body().getIV())));
                                MainActivity.modeleText.setText(Decrypt.decrypt(response.body().getModele(),Base64.getDecoder().decode(response.body().getKey()),Base64.getDecoder().decode(response.body().getIV())));
                                MainActivity.energieText.setText(Decrypt.decrypt(response.body().getEnergieNGC(),Base64.getDecoder().decode(response.body().getKey()),Base64.getDecoder().decode(response.body().getIV())));
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
    }*/

}
