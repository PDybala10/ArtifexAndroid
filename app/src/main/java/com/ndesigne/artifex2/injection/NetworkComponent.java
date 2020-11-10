package com.ndesigne.artifex2.injection;

import com.ndesigne.artifex2.view.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworksModule.class})
public interface NetworkComponent {
    public void inject(MainActivity activity);
}
