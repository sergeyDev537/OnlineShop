package com.most4dev.onlineshop.domain.usecases

import com.most4dev.onlineshop.domain.entities.ItemCategory
import com.most4dev.onlineshop.domain.repositories.ProductRepository

class GetCategoryProductsUseCase(private val productRepository: ProductRepository) {

    operator fun invoke():List<ItemCategory>{
        return productRepository.getCategoryProducts()
    }

}