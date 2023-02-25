package com.most4dev.onlineshop.domain.usecases

import com.most4dev.onlineshop.domain.entities.AccountEntity
import com.most4dev.onlineshop.domain.repositories.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(account: AccountEntity){
        authRepository.login(account)
    }

}