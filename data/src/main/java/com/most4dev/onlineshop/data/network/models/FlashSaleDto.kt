package com.most4dev.onlineshop.data.network.models

import com.google.gson.annotations.SerializedName

data class FlashSaleDto(
    @SerializedName("category")
    val category: String,
    @SerializedName("discount")
    val discount: Int,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double
)