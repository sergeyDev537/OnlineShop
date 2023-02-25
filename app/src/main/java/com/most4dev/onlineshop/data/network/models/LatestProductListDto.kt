package com.most4dev.onlineshop.data.network.models

import com.google.gson.annotations.SerializedName

data class LatestProductListDto(
    @SerializedName("latest")
    val latest: List<LatestProductDto>
)