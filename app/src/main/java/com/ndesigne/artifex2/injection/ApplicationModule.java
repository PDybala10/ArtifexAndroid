package com.ndesigne.artifex2.injection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ndesigne.artifex2.viewModel.MainViewModel;
import com.ndesigne.artifex2.viewModel.APIinterface;

import javax.inject.Singleton;


import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApplicationModule {
    /*private String urlPath;

   public NetworksModule(){
       this.urlPath = urlPath;
    } */

    @Singleton
    @Provides
    public String provideurlPath(){

        return "http://10.0.2.2:14950/";
    }

    @Singleton
    @Provides
    public Gson provideGson(){
        GsonBuilder builder = new GsonBuilder();
        return builder.create();

    }
    @Singleton
    @Provides
    public Retrofit provideRetrofit(Gson gson, String urlPath){
        Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl(urlPath)
                 .addConverterFactory(GsonConverterFactory.create(gson))
                 .build();
        return retrofit;
    }

    @Singleton
    @Provides
    public APIinterface provideAPIinterface(Retrofit retrofit){
        APIinterface apIinterface = retrofit.create(APIinterface.class);
        return apIinterface;
    }

    @Provides
    public MainViewModel provideMainViewModel(){
        MainViewModel mainViewModel = new MainViewModel();
        mainViewModel.init();
        return mainViewModel;
    }


}
