package com.ndesigne.artifex2.viewModel.repository;

import com.ndesigne.artifex2.viewModel.remote.APIinterface;

import javax.inject.Inject;

public class ArtiRepository {
    private  APIinterface apIinterface;

    @Inject
    public ArtiRepository( APIinterface apIinterface) {

        this.apIinterface = apIinterface;
    }

}
