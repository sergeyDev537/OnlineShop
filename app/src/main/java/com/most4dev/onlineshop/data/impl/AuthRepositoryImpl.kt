package com.most4dev.onlineshop.data.impl

import com.most4dev.onlineshop.data.database.dao.AccountDao
import com.most4dev.onlineshop.data.mappers.AccountMapper
import com.most4dev.onlineshop.domain.entities.AccountEntity
import com.most4dev.onlineshop.domain.repositories.AuthRepository

class AuthRepositoryImpl(
    private val accountDao: AccountDao,
    private val accountMapper: AccountMapper
): AuthRepository {

    override suspend fun signIn(account: AccountEntity) {
        accountDao.createAccount(accountMapper.mapEntityToDbModel(account))
    }

    override suspend fun login(firstName: String, password: String): Boolean {
        val currentUser = accountDao.getAccount().value
        currentUser?.let {
            return it.firstName == firstName && it.password == password
        } ?: return false
    }

    override suspend fun logout(account: AccountEntity) {
        accountDao.removeUser(accountMapper.mapEntityToDbModel(account))
    }
}