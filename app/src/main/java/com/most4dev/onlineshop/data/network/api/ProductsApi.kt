package com.most4dev.onlineshop.data.network.api

import com.most4dev.onlineshop.data.network.models.FlashSaleListDto
import com.most4dev.onlineshop.data.network.models.ItemProductDto
import com.most4dev.onlineshop.data.network.models.LatestProductListDto
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {

    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatestProducts(): Response<LatestProductListDto>

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getSaleProducts(): Response<FlashSaleListDto>

    @GET("f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getItemProduct(): Response<ItemProductDto>

}