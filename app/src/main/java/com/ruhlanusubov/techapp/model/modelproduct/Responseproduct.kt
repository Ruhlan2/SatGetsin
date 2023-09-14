package com.ruhlanusubov.techapp.model.modelproduct


import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
data class Responseproduct(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("products")
    val products: List<Product>?,
    @SerializedName("skip")
    val skip: Int?,
    @SerializedName("total")
    val total: Int?
):Parcelable