package com.ndesigne.artifex2.view.main;

import android.app.Application;

import com.ndesigne.artifex2.injection.DaggerNetworkComponent;
import com.ndesigne.artifex2.injection.NetworkComponent;
import com.ndesigne.artifex2.injection.NetworksModule;


public class ArtiApplication extends Application {

    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        networkComponent = DaggerNetworkComponent.builder()
                .networksModule(new NetworksModule(MainActivity.base_url))
                .build();
    }
    public NetworkComponent getNetworkComponent(){
        return networkComponent;
    }
}
