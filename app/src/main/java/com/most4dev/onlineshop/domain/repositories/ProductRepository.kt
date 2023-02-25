package com.most4dev.onlineshop.domain.repositories

import com.most4dev.onlineshop.domain.entities.BrandEntity
import com.most4dev.onlineshop.domain.entities.ItemCategory
import com.most4dev.onlineshop.domain.entities.ProductEntity
import com.most4dev.onlineshop.domain.entities.SaleProductEntity

interface ProductRepository {

    fun searchProduct(nameProduct: String): List<ProductEntity>

    fun getCategoryProducts(): List<ItemCategory>

    suspend fun getLatestProducts(): List<ProductEntity>

    suspend fun getFlashSaleProducts(): List<SaleProductEntity>

    fun getBrands(): List<BrandEntity>

    fun getProduct(id: Long): ProductEntity

}