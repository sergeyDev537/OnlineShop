package com.most4dev.onlineshop.domain.repositories

import com.most4dev.onlineshop.domain.entities.AccountEntity

interface AuthRepository {

    suspend fun signIn(account: AccountEntity): Boolean

    suspend fun login(firstName: String, password: String): Boolean

    suspend fun logout(account: AccountEntity)

}