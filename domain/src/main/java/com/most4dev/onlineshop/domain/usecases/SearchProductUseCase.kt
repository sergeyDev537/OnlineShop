package com.most4dev.onlineshop.domain.usecases

import android.database.MatrixCursor
import com.most4dev.onlineshop.domain.repositories.ProductRepository

class SearchProductUseCase(private val productRepository: ProductRepository) {

    suspend operator fun invoke(nameProduct: String): MatrixCursor {
        return productRepository.searchProduct(nameProduct)
    }

}