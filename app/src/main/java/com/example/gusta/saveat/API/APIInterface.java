package com.example.gusta.saveat.API;

import com.example.gusta.saveat.model.Product;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by gusta on 25/08/2017.
 */

public interface APIInterface {
    @GET("products")
    Call<Product[]> getProduct(@QueryMap Map<String, String> params);

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") String id);

    @POST("products")
    Call<Product> postProduct(@QueryMap Map<String, String> params);

    @PUT("products")
    Call<Product> updateProduct(@QueryMap Map<String, String> params);
}
