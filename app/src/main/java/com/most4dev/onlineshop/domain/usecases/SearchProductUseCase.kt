package com.most4dev.onlineshop.domain.usecases

import com.most4dev.onlineshop.domain.entities.ProductEntity
import com.most4dev.onlineshop.domain.repositories.ProductRepository

class SearchProductUseCase(private val productRepository: ProductRepository) {

    operator fun invoke(nameProduct: String): List<ProductEntity>{
        return productRepository.searchProduct(nameProduct)
    }

}