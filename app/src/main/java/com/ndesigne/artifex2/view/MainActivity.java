package com.ndesigne.artifex2.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;

import com.google.gson.Gson;
import com.ndesigne.artifex2.BuildConfig;
import com.ndesigne.artifex2.R;
import com.ndesigne.artifex2.injection.ArtiApplication;
import com.ndesigne.artifex2.model.AES.AES_GCM_Init;
import com.ndesigne.artifex2.model.entities.Arti;
import com.ndesigne.artifex2.model.entities.Image;
import com.ndesigne.artifex2.model.entities.Payload;
import com.ndesigne.artifex2.model.threads.Threads;
import com.ndesigne.artifex2.model.threads.ThreadsDecrypt;
import com.ndesigne.artifex2.viewModel.APIinterface;
import com.ndesigne.artifex2.viewModel.MainViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;

    public static TextView marqueText, modeleText, version_comText, energieText, transmissionText, categorieText, descriptionText, anneeText;
    public static TextView an_mise_en_circulationText, kilometrageText, prixText, prem_mainText, voitureID, clientID;
    public static ImageView imageView;
    public static int clientIdGlobal = 1;
    public static Image image = new Image(1, 0, "dreamTe", null, "picture");
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
    AES_GCM_Init aes;
    Threads threads;
    ThreadsDecrypt threadsDecrypt;
    private Button boutonPostText;
    private Button boutonPostImage;
    private LinearLayout visileBouton;
    private LinearLayout affiche;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        marqueText = findViewById(R.id.marqueText);
        voitureID = findViewById(R.id.voitureID);
        clientID = findViewById(R.id.clientID);
        modeleText = findViewById(R.id.modeleText);
        version_comText = findViewById(R.id.version);
        energieText = findViewById(R.id.energie);
        transmissionText = findViewById(R.id.transmission);
        categorieText = findViewById(R.id.carosserie);
        descriptionText = findViewById(R.id.description);
        anneeText = findViewById(R.id.annee);
        an_mise_en_circulationText = findViewById(R.id.anneMiseCircu);
        kilometrageText = findViewById(R.id.kilometrage);
        prixText = findViewById(R.id.prix);
        prem_mainText = findViewById(R.id.premierMain);


        boutonPostText = findViewById(R.id.post);
        boutonPostImage = findViewById(R.id.postImage);
        visileBouton = findViewById(R.id.visileBouton);
        affiche = findViewById(R.id.affiche);
        imageView = findViewById(R.id.imageView);
        aes = AES_GCM_Init.getInstance();
        threads = new Threads();
        threadsDecrypt = new ThreadsDecrypt();

        ((ArtiApplication) getApplication()).getNetworkComponent().inject(MainActivity.this);

        MainViewModel.mutableArti.observe(this, new Observer<Arti>() {
            @Override
            public void onChanged(Arti arti) {
                clientIdGlobal = arti.getClientId();
                clientID.setText(String.valueOf(arti.getClientId()));
                voitureID.setText(String.valueOf(arti.getVoitureId()));
                modeleText.setText(arti.getModele());
                marqueText.setText(arti.getMarque());
                version_comText.setText(arti.getVersion_com());
                energieText.setText(arti.getEnergie());
                transmissionText.setText(arti.getTransmission());
                categorieText.setText(arti.getCategorie());
                descriptionText.setText(arti.getDescription());
                anneeText.setText(String.valueOf(arti.getAnnee()));
                an_mise_en_circulationText.setText(String.valueOf(arti.getAn_mise_en_circulation()));
                kilometrageText.setText(String.valueOf(arti.getKilometrage()));
                prixText.setText(String.valueOf(arti.getPrix()));
                prem_mainText.setText(String.valueOf(arti.isPrem_main()));

            }
        });


        MainViewModel.mutableLiveImage.observe(this, new Observer<Image>() {
            @Override
            public void onChanged(Image s) {
                if (image.getImage() != null) {
                    byte[] decodedString = Base64.decode(image.getImage(), Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                    imageView.setImageBitmap(decodedByte);
                }
            }
        });

        MainViewModel.mutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                apiCallData();
            }
        });

        MainViewModel.mutableLiveDecrypt.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                int pos = s.indexOf("picture");
                if (pos == -1) {
                    Arti arti = gson.fromJson(s, Arti.class);
                    Toast.makeText(getApplicationContext(), "voiture " + arti.getVoitureId() + " du client " +
                                    arti.getClientId() + " ajouter"
                            , Toast.LENGTH_LONG).show();
                    MainViewModel.mutableArti.setValue(arti);
                    image.setVoitureId(arti.getVoitureId());

                } else {
                    Image img = gson.fromJson(s, Image.class);
                    MainViewModel.mutableLiveImage.setValue(img);

                }

            }
        });
        // send data
        boutonPostText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = gson.toJson(MainViewModel.mutableArti.getValue());
                threads.startThread(aes, data);
                affiche.setVisibility(View.VISIBLE);
                visileBouton.setVisibility(View.GONE);
                boutonPostImage.setVisibility(View.VISIBLE);

            }
        });
        // send picture
        boutonPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
                boutonPostImage.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onBackPressed() {
        MainViewModel.init();
        Intent intent = new Intent(getApplicationContext(), Form.class);
        startActivity(intent);
        this.finish();
    }

    @SuppressLint("NewApi")
    private void apiCallData() {

        Payload payload = new Payload(MainViewModel.mutableLiveData.getValue());

        Call<Payload> call = apIinterface.sendData(payload);

        call.enqueue(new Callback<Payload>() {
            @Override
            public void onResponse(Call<Payload> call, Response<Payload> response) {
                try {
                    // call Decrypt Fonction
                    threadsDecrypt.startThread(aes, response);


                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                Toast.makeText(getApplicationContext(), "API SUCCESS", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Payload> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_LONG).show();
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