package com.most4dev.onlineshop.domain.repositories

import android.database.MatrixCursor
import com.most4dev.onlineshop.domain.entities.*

interface ProductRepository {

    suspend fun searchProduct(nameProduct: String): MatrixCursor

    fun getCategoryProducts(): List<ItemCategory>

    suspend fun getLatestProducts(): List<ProductEntity>

    suspend fun getFlashSaleProducts(): List<SaleProductEntity>

    fun getBrands(): List<BrandEntity>

    suspend fun getProduct(): ItemProductEntity?

}