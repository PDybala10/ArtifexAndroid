package com.ndesigne.artifex2.model.threads;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ndesigne.artifex2.model.AES.AES_GCM_Init;
import com.ndesigne.artifex2.model.AES.Encrypt;
import com.ndesigne.artifex2.model.entities.Arti;
import com.ndesigne.artifex2.view.MainActivity;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class Threads extends AppCompatActivity {

    private  volatile boolean stopThread = false;

    public void  startThread(AES_GCM_Init aes, String data) {
        stopThread = false;
        ExampleRunnable runnable = new ExampleRunnable(data, aes);
        new Thread(runnable).start();

    }
    public  void stopThread() {
        stopThread = true;
    }

    class ExampleRunnable implements Runnable {
        String data;
        AES_GCM_Init aes;
        ExampleRunnable(String data, AES_GCM_Init aes) {
            this.data = data;
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

                            MainActivity.data =  Encrypt.encrypt(data.getBytes(), aes.getKey(), aes.getIV());

                            System.out.println("je suis une donnée : "+MainActivity.data);
                             //  System.out.println(MainActivity.arti);
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
