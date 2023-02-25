package com.most4dev.onlineshop.domain.usecases

import com.most4dev.onlineshop.domain.entities.ProductEntity
import com.most4dev.onlineshop.domain.repositories.ProductRepository

class GetLatestProductsUseCase(private val productRepository: ProductRepository) {

    suspend operator fun invoke(): List<ProductEntity>{
        return productRepository.getLatestProducts()
    }

}