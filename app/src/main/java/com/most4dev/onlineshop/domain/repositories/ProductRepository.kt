package com.most4dev.onlineshop.domain.repositories

import com.most4dev.onlineshop.domain.entities.*
import java.util.*

interface ProductRepository {

    fun searchProduct(nameProduct: String): List<ProductEntity>

    fun getCategoryProducts(): List<ItemCategory>

    suspend fun getLatestProducts(): List<ProductEntity>

    suspend fun getFlashSaleProducts(): List<SaleProductEntity>

    fun getBrands(): List<BrandEntity>

    suspend fun getProduct(): ItemProductEntity?

}