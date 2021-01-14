package com.ndesigne.artifex2.model.AES;


import android.annotation.SuppressLint;
import android.os.Build;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class Encrypt {
	
	public static final int GCM_TAG_LENGTH = 128;

	

    @SuppressLint("NewApi")
    public static String encrypt(byte[] plaintext, byte[] key, byte[] IV) throws Exception
    {
        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        
        // Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        
        // Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, IV);
       // System.out.println("je suis le tagg "+gcmParameterSpec);
        
        // Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);

        // Perform Encryption
        byte[] cipherText = cipher.doFinal(plaintext);

        byte[] tagVal = Arrays.copyOfRange(cipherText, cipherText.length - (128 / Byte.SIZE), cipherText.length);
        //String tag  = Base64.getEncoder().encodeToString(tagVal);
        //System.out.println("Tag value : "+ tag);

        byte[] clippedCipherText =  Arrays.copyOfRange(cipherText, 0, cipherText.length - (128 / Byte.SIZE)  );

        //System.out.println("clippedCipherText value : "+ Base64.getEncoder().encodeToString(clippedCipherText));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        outputStream.write( key );
        System.out.println("key :"+key.length);
        outputStream.write( IV );
        System.out.println("IV :"+IV.length);
        outputStream.write( tagVal );
        System.out.println("tag :"+tagVal.length);
        outputStream.write( clippedCipherText );
        System.out.println("tag :"+clippedCipherText.length);

        byte addedEncyptedVal[] = outputStream.toByteArray();

            return Base64.getEncoder().encodeToString(addedEncyptedVal);

    }

}
