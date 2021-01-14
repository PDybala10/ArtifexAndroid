package com.ndesigne.artifex2.injection;

import android.app.Application;

public class ArtiApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .build();
    }
    public ApplicationComponent getNetworkComponent(){
        return applicationComponent;
    }
}
