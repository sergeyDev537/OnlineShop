package com.most4dev.onlineshop.domain.usecases

import com.most4dev.onlineshop.domain.entities.ProductEntity
import com.most4dev.onlineshop.domain.repositories.ProductRepository
import java.util.*

class GetProductUseCase(private val productRepository: ProductRepository) {

    operator fun invoke(productID: UUID): ProductEntity?{
        return productRepository.getProduct(productID)
    }

}