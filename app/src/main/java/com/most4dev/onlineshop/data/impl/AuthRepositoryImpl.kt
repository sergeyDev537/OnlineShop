package com.most4dev.onlineshop.data.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.most4dev.onlineshop.data.database.dao.AccountDao
import com.most4dev.onlineshop.data.mappers.AccountMapper
import com.most4dev.onlineshop.domain.entities.AccountEntity
import com.most4dev.onlineshop.domain.repositories.AuthRepository

class AuthRepositoryImpl(
    private val accountDao: AccountDao,
    private val accountMapper: AccountMapper,
) : AuthRepository {

    private var listAccounts = listOf<AccountEntity>()

    override suspend fun signIn(account: AccountEntity): Boolean {
        getAccounts()
        return if (!checkAccountEmail(account)){
            accountDao.createAccount(accountMapper.mapEntityToDbModel(account))
            true
        } else{
            false
        }
    }

    private fun checkAccountEmail(account: AccountEntity): Boolean {
        var booleanCheck = false
        for (accountItem in listAccounts) {
            if (accountItem.email == account.email) {
                booleanCheck = true
                break
            } else {
                booleanCheck = false
            }
        }
        return booleanCheck
    }

    override suspend fun login(firstName: String, password: String): Boolean {
        getAccounts()
        var booleanCheck = false
        for (accountItem in listAccounts){
            if (accountItem.firstName == firstName && accountItem.password == password){
                booleanCheck = true
                break
            }
            else{
                booleanCheck = false
            }
        }
        return booleanCheck
    }

    override suspend fun logout(account: AccountEntity) {
        accountDao.removeUser(accountMapper.mapEntityToDbModel(account))
    }

    override suspend fun getAccounts(): List<AccountEntity>{
        listAccounts = accountDao.getAccount().map {
            accountMapper.mapDbModelToEntity(it)
        }
        return listAccounts
    }

}