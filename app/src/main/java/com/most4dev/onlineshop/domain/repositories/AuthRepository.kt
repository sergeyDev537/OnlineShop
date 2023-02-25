package com.most4dev.onlineshop.domain.repositories

import com.most4dev.onlineshop.domain.entities.AccountEntity

interface AuthRepository {

    suspend fun signIn(account: AccountEntity)

    suspend fun login(account: AccountEntity)

    suspend fun logout(account: AccountEntity)

}