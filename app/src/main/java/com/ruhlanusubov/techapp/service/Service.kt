package com.ruhlanusubov.techapp.service

import com.ruhlanusubov.techapp.model.detailuser.UserDetail
import com.ruhlanusubov.techapp.model.modelcategory.Category
import com.ruhlanusubov.techapp.model.modelproduct.Responseproduct
import com.ruhlanusubov.techapp.model.modeluser.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("users/{user_id}")
    fun detailUser(@Path("id") id:Int):Call<UserDetail>
    @POST("auth/login")
    @FormUrlEncoded
    fun loginuser(@Field("username") username:String,@Field("password") password:String):Call<UserResponse>

    @GET("products")
    fun getproduct():Call<Responseproduct>

    @GET("products/categories")
    fun getcategories():Call<Category>


    @GET("products/category/{category_name}")
    fun filter(
        @Path("category_name") title:String,
        @Query("limit") limit:Int
    ):Call<Responseproduct>


    @GET("products")
    fun onlyLimit(
        @Query("limit") limit:Int
    ):Call<Responseproduct>

    @GET("product/search")
    fun onlySearch(
        @Query("q") q:String
    ):Call<Responseproduct>

    @GET("product/category/{category_name}")
    fun onlyCategory(
        @Path("category_name") title:String
    ):Call<Responseproduct>

}