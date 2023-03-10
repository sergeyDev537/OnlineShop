package com.most4dev.onlineshop.domain.usecases

import com.most4dev.onlineshop.domain.repositories.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(firstName: String, password: String): Boolean{
        return authRepository.login(firstName, password)
    }

}