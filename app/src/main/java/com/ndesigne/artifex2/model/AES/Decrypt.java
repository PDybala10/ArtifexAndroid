package com.ndesigne.artifex2.model.AES;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import android.util.Log;

import static android.content.ContentValues.TAG;


public class Decrypt {

	public static final int GCM_TAG_LENGTH = 128;
    private Handler mainHandler = new Handler();
    private volatile boolean stopThread = false;

    @SuppressLint("NewApi") //public static String decrypt(String cipherText, byte[] key, byte[] IV) throws Exception
    public static String decrypt(String cipherText) throws Exception
    {

        byte[] key = new byte[16];
        byte[] IV = new byte[7];
        byte[] tag = new byte[16];
        byte[] clippedCipherText = new byte[Base64.getDecoder().decode(cipherText).length-39];
        //System.out.println("j'ai une taille de :"+clippedCipherText.length);
        InputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(cipherText));


     try {
         inputStream.read(key,0,16 );
        // System.out.println(Base64.getEncoder().encodeToString(key));
         inputStream.read(IV,0,7 );
        // System.out.println(Base64.getEncoder().encodeToString(IV));

         inputStream.read(tag,0,16 );
         inputStream.read(clippedCipherText,0,Base64.getDecoder().decode(cipherText).length - 39);


     }catch (IOException e){
         e.getMessage();
     }



        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write( clippedCipherText );
        outputStream.write( tag );


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
