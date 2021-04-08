package com.ndesigne.artifex2.model.AES;

import android.annotation.SuppressLint;
import android.os.Handler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class Decrypt {

    //comment
    public static final int GCM_TAG_LENGTH = 128;
    private final Handler mainHandler = new Handler();
    private final boolean stopThread = false;

    @SuppressLint("NewApi")
    public static String decrypt(String cipherText) throws Exception {

        byte[] key = new byte[16];
        byte[] IV = new byte[7];
        byte[] tag = new byte[16];
        byte[] clippedCipherText = new byte[Base64.getDecoder().decode(cipherText).length - 39];
        InputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(cipherText));


        try {
            inputStream.read(key, 0, 16);
            inputStream.read(IV, 0, 7);

            inputStream.read(tag, 0, 16);
            inputStream.read(clippedCipherText, 0, Base64.getDecoder().decode(cipherText).length - 39);


        } catch (IOException e) {
            e.getMessage();
        }


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(clippedCipherText);
        outputStream.write(tag);


        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        // Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

        // Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, IV);

        // Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);

        // Perform Decryption
        byte[] decryptedText = cipher.doFinal(outputStream.toByteArray());


        return new String(decryptedText);
    }


}
