package com.ndesigne.artifex2.model.threads;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

import com.ndesigne.artifex2.model.AES.AES_GCM_Init;
import com.ndesigne.artifex2.model.AES.Encrypt;
import com.ndesigne.artifex2.viewModel.MainViewModel;


public class Threads extends AppCompatActivity {

    private volatile boolean stopThread = false;

    public void startThread(AES_GCM_Init aes, String data) {
        stopThread = false;
        ExampleRunnable runnable = new ExampleRunnable(data, aes);
        new Thread(runnable).start();

    }

    public void stopThread() {
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

            if (stopThread)
                return;

            runOnUiThread(new Runnable() {
                @SuppressLint("NewApi")
                @Override
                public void run() {
                    try {


                        MainViewModel.mutableLiveData.setValue(Encrypt.encrypt(data.getBytes(), aes.getKey(), aes.getIV()));


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}