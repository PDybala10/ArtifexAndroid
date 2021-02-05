package com.ndesigne.artifex2.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ndesigne.artifex2.BuildConfig;
import com.ndesigne.artifex2.R;
import com.ndesigne.artifex2.function.Function;
import com.ndesigne.artifex2.injection.ArtiApplication;
import com.ndesigne.artifex2.model.AES.AES_GCM_Init;
import com.ndesigne.artifex2.model.AES.Decrypt;
import com.ndesigne.artifex2.model.AES.Encrypt;
import com.ndesigne.artifex2.model.entities.Arti;
import com.ndesigne.artifex2.model.entities.Image;
import com.ndesigne.artifex2.model.entities.Payload;
import com.ndesigne.artifex2.model.threads.Threads;
import com.ndesigne.artifex2.model.threads.ThreadsDecrypt;
import com.ndesigne.artifex2.viewModel.APIinterface;
import com.ndesigne.artifex2.viewModel.MainViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    @Inject
    Gson gson;

    @Inject
    APIinterface apIinterface;

    @Inject
    MainViewModel mainViewModel;

    @Inject
    String base_url;

    public static TextView clientidText,marqueText,modeleText,energieText,immatText;
    public static ImageView imageView;
    private Button button;
    public static String data;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;

    public static Arti arti = new Arti("Mercedes","classe A+", "Essence","AA-455-ZE","dreamTe","data");
    public  static Image image = new Image("dreamTe",null,"picture");
    AES_GCM_Init aes;
    Threads threads, threads1;
    ThreadsDecrypt threadsDecrypt;
    ArrayList<String> carList;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        carList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        immatText = findViewById(R.id.immatText);
        marqueText = findViewById(R.id.marqueText);
        modeleText = findViewById(R.id.modeleText);
        energieText = findViewById(R.id.energieText);
        button = findViewById(R.id.buttonPost);
        imageView = findViewById(R.id.imageView);
        aes = AES_GCM_Init.getInstance();
        threads = new Threads();
        threadsDecrypt = new ThreadsDecrypt();

        carList.add(marqueText.getText().toString());
        carList.add(modeleText.getText().toString());
        carList.add(energieText.getText().toString());
        carList.add(immatText.getText().toString());

        //ajouter image
       /* ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.image);
        icon.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageBytes = stream.toByteArray();
        String image = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        arti.setImage(image);*/
        ((ArtiApplication) getApplication()).getNetworkComponent().inject(MainActivity.this);

        mainViewModel.mutableArti.observe(this, new Observer<Arti>() {
            @Override
            public void onChanged(Arti arti) {
                modeleText.setText(arti.getModele());
                energieText.setText(arti.getEnergieNGC());
                immatText.setText(arti.getImmat());
                marqueText.setText(arti.getMarque());

                //image
                if(image.getImage() != null) {
                    byte[] decodedString = Base64.decode(image.getImage(), Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                    imageView.setImageBitmap(decodedByte);
                }
            }
        });

        mainViewModel.mutableLiveImage.observe(this, new Observer<Image>() {
            @Override
            public void onChanged(Image s) {
                if(image.getImage() != null) {
                    byte[] decodedString = Base64.decode(image.getImage(), Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                    imageView.setImageBitmap(decodedByte);
                }
            }
        });

        mainViewModel.mutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                apiCallData();
            }
        });

        mainViewModel.mutableLiveDecrypt.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                int pos = s.indexOf("picture");
                if(pos == -1){
                   // Gson gson = new GsonBuilder().create();
                    Arti arti = gson.fromJson(s,Arti.class);
                    MainViewModel.mutableArti.setValue(arti);
                }
                else {
                    Image img = gson.fromJson(s,Image.class);
                    //Gson gson = new GsonBuilder().create();
                    MainViewModel.mutableLiveImage.setValue(img);
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = gson.toJson(arti);

                threads.startThread(aes, data);

               /* System.out.println("toute la donnée: "+data);
                System.out.println("toute la donnée: "+data);*/
                dispatchTakePictureIntent();
            }
        });


    }

    @SuppressLint("NewApi")
    private void apiCallData() {

        Payload payload  = new Payload(MainViewModel.mutableLiveData.getValue());
        Call<Payload> call = apIinterface.sendData(payload);
        call.enqueue(new Callback<Payload>() {
            @Override
            public void onResponse(Call<Payload> call, Response<Payload> response) {
                try {

                    threadsDecrypt.startThread(aes,response);


                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("TEST OK");

                Toast.makeText(getApplicationContext(), "API SUCCESSSSSSSSSSSSSSSSSSSS", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Payload> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getApplicationContext(), "noooooooooooooooooooooo", Toast.LENGTH_LONG).show();
            }
        });

        threads.stopThread();
        threadsDecrypt.stopThread();

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.i("Vlad", "No photo app activity found");
            }
            Log.i("Vlad", "BuildConfig.APP_ID = " + BuildConfig.APPLICATION_ID);
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.ndesigne.artifex2.android.plateformeauto.fileprovider",
                        photoFile);
                takePictureIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File internalFileDir = new File(getFilesDir(), "images/toto");
        if (!internalFileDir.exists()) {
            internalFileDir.mkdirs();
        }
        File delTestFile = new File(getFilesDir(), "images/mytest.jpg");
        if (delTestFile.exists()) delTestFile.delete();
        return new File(internalFileDir, "mytest.jpg");
    }
    File getPhotoFile(String fileName) {
        return new File(getFilesDir(), "images/toto/mytest.jpg");
    }

    File getScaledPhotoFile() {
        return new File(getFilesDir(), "images/toto/mytest_scaled.jpg");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Log.i("Vlad", "data to be checked = ");
            Log.i("Vlad", "data to be checked = ");
            File origFile = getPhotoFile("");
            Bitmap origBitmap = BitmapFactory.decodeFile(origFile.getAbsolutePath());
            int origWidth = origBitmap.getWidth();
            int origHeight = origBitmap.getHeight();
            final int destWidth = 400;
            float scaleFactor = 1;
            if (origWidth / destWidth > 1) scaleFactor = origWidth / destWidth;
            int destHeight = (int) (origHeight / scaleFactor);

            Bitmap destBitmap = Bitmap.createScaledBitmap(origBitmap, destWidth, destHeight, false);
            ByteArrayOutputStream destOutStream = new ByteArrayOutputStream();
            destBitmap.compress(Bitmap.CompressFormat.JPEG, 80, destOutStream);
            byte[] imageBytes = destOutStream.toByteArray();
            String imageStr = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            image.setImage(imageStr);
             String dataJ = gson.toJson(image);

             threads.startThread(aes, dataJ);
             System.out.println("toute la donnée: "+dataJ);
             System.out.println("toute la donnée: "+dataJ);
           // mImageView.setImageBitmap(destBitmap);
            File destFile = getScaledPhotoFile();
            try {
                destFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(destFile);
                fos.write(destOutStream.toByteArray());
                fos.close();
                destOutStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}