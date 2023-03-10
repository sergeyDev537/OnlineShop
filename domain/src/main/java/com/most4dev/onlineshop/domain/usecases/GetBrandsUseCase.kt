package com.most4dev.onlineshop.domain.usecases

import com.most4dev.onlineshop.domain.entities.BrandEntity
import com.most4dev.onlineshop.domain.repositories.ProductRepository

class GetBrandsUseCase(private val productRepository: ProductRepository) {

    operator fun invoke(): List<BrandEntity>{
        return productRepository.getBrands()
    }

}