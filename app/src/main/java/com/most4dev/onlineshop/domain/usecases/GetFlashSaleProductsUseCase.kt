package com.most4dev.onlineshop.domain.usecases

import com.most4dev.onlineshop.domain.entities.SaleProductEntity
import com.most4dev.onlineshop.domain.repositories.ProductRepository

class GetFlashSaleProductsUseCase(private val productRepository: ProductRepository) {

    suspend operator fun invoke(): List<SaleProductEntity>{
        return productRepository.getFlashSaleProducts()
    }

}