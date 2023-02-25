package com.most4dev.onlineshop.domain.usecases

import com.most4dev.onlineshop.domain.entities.ProductEntity
import com.most4dev.onlineshop.domain.repositories.ProductRepository

class GetProductUseCase(private val productRepository: ProductRepository) {

    operator fun invoke(productID: Long): ProductEntity{
        return productRepository.getProduct(productID)
    }

}