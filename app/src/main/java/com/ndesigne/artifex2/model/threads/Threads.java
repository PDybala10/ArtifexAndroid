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

    public void  startThread(AES_GCM_Init aes,  ArrayList<String> carList) {
        stopThread = false;
        ExampleRunnable runnable = new ExampleRunnable(carList, aes);
        new Thread(runnable).start();

    }
    public  void stopThread() {
        stopThread = true;
    }

    class ExampleRunnable implements Runnable {
        List<String> carList;
        AES_GCM_Init aes;
        ExampleRunnable(ArrayList<String> carList, AES_GCM_Init aes) {
            this.carList = carList;
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
                               MainActivity.arti = new Arti(1,
                                       1,
                                       Encrypt.encrypt(carList.get(0).getBytes(), aes.getKey(), aes.getIV()) ,
                                       Encrypt.encrypt(carList.get(1).getBytes(), aes.getKey(), aes.getIV()) ,
                                       Encrypt.encrypt(carList.get(2).getBytes(), aes.getKey(), aes.getIV()) ,
                                       Encrypt.encrypt(carList.get(3).getBytes(), aes.getKey(), aes.getIV()) ,
                                       Base64.getEncoder().encodeToString(aes.getKey()),
                                       Base64.getEncoder().encodeToString(aes.getIV())
                               );
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
