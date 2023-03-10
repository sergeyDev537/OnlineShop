package com.most4dev.onlineshop.domain.usecases

import com.most4dev.onlineshop.domain.entities.AccountEntity
import com.most4dev.onlineshop.domain.repositories.AuthRepository

class SignInUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(account: AccountEntity): Boolean{
        return authRepository.signIn(account)
    }

}