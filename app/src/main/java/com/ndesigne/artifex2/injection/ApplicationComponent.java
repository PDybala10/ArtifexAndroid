package com.ndesigne.artifex2.injection;


import com.ndesigne.artifex2.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MainActivity activity);

}


