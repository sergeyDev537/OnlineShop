package com.most4dev.onlineshop.domain.entities

import java.util.*

data class ProductEntity(
    val id: UUID,
    val category: String,
    val image_urls: List<String>,
    val name: String,
    val price: Double,
    val description: String,
    val rating: Int,
    val colors: List<Int>,
    val favorite: Boolean
)