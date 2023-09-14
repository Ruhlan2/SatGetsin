package com.ruhlanusubov.techapp.model.modelproduct


import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
data class Product(
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("discountPercentage")
    val discountPercentage: Double?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("images")
    val images: List<String>?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("stock")
    val stock: Int?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("title")
    val title: String?
):Parcelable