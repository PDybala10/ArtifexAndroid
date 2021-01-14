package com.ndesigne.artifex2.model.threads;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

import com.ndesigne.artifex2.function.Function;
import com.ndesigne.artifex2.model.AES.AES_GCM_Init;
import com.ndesigne.artifex2.model.AES.Decrypt;
import com.ndesigne.artifex2.model.AES.Encrypt;
import com.ndesigne.artifex2.model.entities.Arti;
import com.ndesigne.artifex2.model.entities.Payload;
import com.ndesigne.artifex2.view.MainActivity;

import java.util.Base64;

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
                               /* System.out.println("je suis ll'IV "+response.body().getIV());
                                MainActivity.immatText.setText(Decrypt.decrypt(response.body().getImmat(),Base64.getDecoder().decode(response.body().getKey()),Base64.getDecoder().decode(response.body().getIV())));
                                MainActivity.clientidText.setText("Liste de voiture Pour id: "+response.body().getClientid());
                                MainActivity.marqueText.setText(Decrypt.decrypt(response.body().getMarque(),Base64.getDecoder().decode(response.body().getKey()),Base64.getDecoder().decode(response.body().getIV())));
                                MainActivity.modeleText.setText(Decrypt.decrypt(response.body().getModele(),Base64.getDecoder().decode(response.body().getKey()),Base64.getDecoder().decode(response.body().getIV())));
                                MainActivity.energieText.setText(Decrypt.decrypt(response.body().getEnergieNGC(),Base64.getDecoder().decode(response.body().getKey()),Base64.getDecoder().decode(response.body().getIV())));*/
                                response.body().setData(Decrypt.decrypt(response.body().getData()));

                                Arti newArti = Function.splitme(response.body().getData());

                                MainActivity.immatText.setText(newArti.getImmat());

                                MainActivity.marqueText.setText(newArti.getMarque());

                                MainActivity.modeleText.setText(newArti.getModele());

                                MainActivity.energieText.setText(newArti.getEnergieNGC());
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
