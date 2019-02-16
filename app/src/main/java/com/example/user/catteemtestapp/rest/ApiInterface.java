package com.example.user.catteemtestapp.rest;

import com.example.user.catteemtestapp.Activities.Login.loginpogo;
import com.example.user.catteemtestapp.Model.CategoriesPogo;
import com.example.user.catteemtestapp.Model.Foodpogo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("categories_api")
    Call<List<CategoriesPogo>> categoriesid ();

    @GET("food_item_api.php?")
    Call<List<Foodpogo>> food (@Query( "category_id") String category_id);
    @FormUrlEncoded
    @POST("user_login_api.php")
    Call<List<loginpogo>> login (
            @Field( "user_contact") String contact,
            @Field( "user_password") String password
    );







}
