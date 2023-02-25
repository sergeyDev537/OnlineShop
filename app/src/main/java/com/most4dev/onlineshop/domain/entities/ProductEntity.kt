package com.most4dev.onlineshop.domain.entities

data class ProductEntity(
    val category: String,
    val image_urls: List<String>,
    val name: String,
    val price: Int,
    val description: String,
    val rating: Int,
    val colors: List<Int>,
    val favorite: Boolean
)