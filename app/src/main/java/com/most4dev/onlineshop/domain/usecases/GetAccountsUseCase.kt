package com.most4dev.onlineshop.domain.usecases

import com.most4dev.onlineshop.domain.entities.AccountEntity
import com.most4dev.onlineshop.domain.repositories.AuthRepository

class GetAccountsUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(): List<AccountEntity>{
        return authRepository.getAccounts()
    }

}