package com.most4dev.onlineshop.domain.usecases

import com.most4dev.onlineshop.domain.entities.ItemProductEntity
import com.most4dev.onlineshop.domain.repositories.ProductRepository

class GetProductUseCase(private val productRepository: ProductRepository) {

    suspend operator fun invoke(): ItemProductEntity?{
        return productRepository.getProduct()
    }

}