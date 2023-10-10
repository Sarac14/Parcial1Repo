package com.example.parcial1.api;

import com.example.parcial1.dto.ProductoList;
import com.example.parcial1.dto.ProductoSingle;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("products")
    Call<ProductoList> findAll();

    @GET("products/{id}")
    Call<ProductoSingle> find(@Path("id") int id);


}
