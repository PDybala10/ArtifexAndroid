package com.ndesigne.artifex2.viewModel;


import com.ndesigne.artifex2.model.entities.Payload;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface APIinterface {


    /* @GET("/api/mobilecli/{clientid}/cars")
     Call<Payload> getArti(@Path("clientid") int clientid);

     @GET("/api/mobilecli/:clientid/cars/list?carslist=1,2,3")
     Call<List<Arti>> getListArti();

     /*  @FormUrlEncoded
       @POST("/crypt/post")
       Call<Arti> sendData(@Field("key") String key,
                              @Field("message") String message);*/
    String API_ROUTE = "/TwJHKBG0NUGwtjqNlmQmLfFvw22jrozlnZTHprsFIagu8dgG9GdbZT7F3Hz9";

    @POST(API_ROUTE)
    Call<Payload> sendData(@Body Payload body);

}
