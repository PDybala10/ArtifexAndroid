package com.ndesigne.artifex2.model.AES;

import android.annotation.SuppressLint;

import java.security.SecureRandom;

import javax.crypto.KeyGenerator;


public class AES_GCM_Init {

    private static final int AES_KEY_SIZE = 128;
    private static final int GCM_IV_LENGTH = 7;
    private static AES_GCM_Init aes;
    private byte[] secretKey;
    private byte[] IV;


    private AES_GCM_Init() {

    }

    @SuppressLint("NewApi")
    public static final AES_GCM_Init getInstance() {

        if (null == aes) {

            aes = new AES_GCM_Init();
            try {
                aes.initialisation();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return aes;
    }

    @SuppressLint("NewApi")
    private void initialisation() throws Exception {

        // Generate Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(AES_KEY_SIZE);
        secretKey = keyGenerator.generateKey().getEncoded();

        //Generate IV
        IV = new byte[GCM_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);


    }

    public byte[] getKey() {
        return secretKey;
    }

    public byte[] getIV() {
        return IV;
    }


}
