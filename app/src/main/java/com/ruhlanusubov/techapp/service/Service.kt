package com.ruhlanusubov.techapp.service

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
    @POST("auth/login")
    @FormUrlEncoded
    fun loginuser(@Field("username") username:String,@Field("password") password:String):Call<UserResponse>

    @GET("products")
    fun getproduct():Call<Responseproduct>

    @GET("products/categories")
    fun getcategories():Call<Category>

    @GET("products/search")
    fun getsearch(@Query("q") q:String):Call<Responseproduct>

    @GET("products")
    fun ascending(@Query("select") select:String):Call<Responseproduct>



}