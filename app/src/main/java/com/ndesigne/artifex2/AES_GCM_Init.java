package com.ndesigne.artifex2;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class AES_GCM_Init{
	
   private static final int AES_KEY_SIZE = 128;
   private static final int GCM_IV_LENGTH = 12;
    
   private byte[] secretKey;
   private byte[] IV;
   private static AES_GCM_Init aes;
  

    private AES_GCM_Init() {
    	
    }
    @SuppressLint("NewApi")
    private void initialisation () throws Exception
    {
    	

    	/* // Generate Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(AES_KEY_SIZE,SecureRandom.getInstanceStrong());
        secretKey = keyGenerator.generateKey();
       
       //Generate IV
        String cle = "This is cle";
        IV = cle.getBytes();
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);*/

        // Generate Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(AES_KEY_SIZE);
        secretKey = keyGenerator.generateKey().getEncoded();

        //Generate IV
        IV = new byte[GCM_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);
    	
   
    }

    @SuppressLint("NewApi")
    public static final AES_GCM_Init getInstance() {
    	
    	if(null == aes) {
    	
	    	aes = new AES_GCM_Init();
	    	try {
				aes.initialisation();
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
    	}
    	
		return aes;
    }
	public byte[] getKey() {
		return secretKey;
	}

	public byte[] getIV() {
		return IV;
	}
      
    
}
