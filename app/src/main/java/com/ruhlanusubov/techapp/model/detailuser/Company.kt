package com.ruhlanusubov.techapp.model.detailuser


import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("address")
    val address: Address?,
    @SerializedName("department")
    val department: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("title")
    val title: String?
)