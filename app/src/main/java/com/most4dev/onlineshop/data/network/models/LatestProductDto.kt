package com.most4dev.onlineshop.data.network.models

import com.google.gson.annotations.SerializedName

data class LatestProductDto(
    @SerializedName("category")
    val category: String,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)