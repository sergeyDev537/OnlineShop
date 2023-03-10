package com.most4dev.onlineshop.domain.entities

data class SaleProductEntity(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
)