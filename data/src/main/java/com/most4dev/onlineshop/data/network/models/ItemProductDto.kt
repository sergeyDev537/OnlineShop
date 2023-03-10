package com.most4dev.onlineshop.data.network.models

import com.google.gson.annotations.SerializedName

class ItemProductDto(
    @SerializedName("colors")
    val colors: List<String>,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_urls")
    val image_urls: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("number_of_reviews")
    val number_of_reviews: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("rating")
    val rating: Double
)