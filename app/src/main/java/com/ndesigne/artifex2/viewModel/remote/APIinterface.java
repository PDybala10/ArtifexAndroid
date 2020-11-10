package com.ndesigne.artifex2.viewModel.remote;


import com.ndesigne.artifex2.model.entities.Arti;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface APIinterface {


    @GET("crypt/listAll")
    Call<List<Arti>> getArti();

  /*  @FormUrlEncoded
    @POST("/crypt/post")
    Call<Arti> sendData(@Field("key") String key,
                           @Field("message") String message);*/
  String API_ROUTE = "/crypt/post";
    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<Arti> sendData(@Body Arti posts);

}
