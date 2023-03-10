package com.most4dev.onlineshop.domain.entities

import java.util.*

data class ProductEntity(
    val id: UUID,
    val category: String,
    val image_urls: String,
    val name: String,
    val price: Double
)