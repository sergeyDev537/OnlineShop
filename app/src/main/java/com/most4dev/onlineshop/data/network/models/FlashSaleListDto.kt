package com.most4dev.onlineshop.data.network.models

import com.google.gson.annotations.SerializedName

data class FlashSaleListDto(
    @SerializedName("flash_sale")
    val flash_sale: List<FlashSaleDto>
)