package com.most4dev.onlineshop.data.impl

import android.content.Context
import com.most4dev.onlineshop.data.database.dao.AccountDao
import com.most4dev.onlineshop.data.mappers.AccountMapper
import com.most4dev.onlineshop.domain.entities.AccountEntity
import com.most4dev.onlineshop.domain.repositories.AuthRepository

class AuthRepositoryImpl(
    private val context: Context,
    private val accountDao: AccountDao,
    private val accountMapper: AccountMapper,
) : AuthRepository {

    private var listAccounts = listOf<AccountEntity>()

    override suspend fun signIn(account: AccountEntity): Boolean {
        getAccounts()
        return if (!checkAccountEmail(account)) {
            accountDao.createAccount(
                accountMapper.mapEntityToDbModel(
                    account,
                    null
                )
            )
            true
        } else {
            false
        }
    }

    private fun checkAccountEmail(account: AccountEntity): Boolean {
        var booleanCheck = false
        for (accountItem in listAccounts) {
            if (accountItem.email == account.email) {
                booleanCheck = true
                saveAuthUser(accountItem.email)
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
        for (accountItem in listAccounts) {
            if (accountItem.firstName == firstName && accountItem.password == password) {
                booleanCheck = true
                saveAuthUser(accountItem.email)
                break
            } else {
                booleanCheck = false
            }
        }
        return booleanCheck
    }

    private fun saveAuthUser(email: String) {
        val sharedPref = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(EMAIL_USER_KEY, email)
            apply()
        }
    }

    private fun removeAuthUser() {
        val sharedPref = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(EMAIL_USER_KEY, DEFAULT_VALUE_EMAIL)
            apply()
        }
    }

    override suspend fun logout(account: AccountEntity) {
        removeAuthUser()
        accountDao.removeUser(
            accountMapper.mapEntityToDbModel(
                account,
                accountMapper.mapBitmapToString(account.photoProfile)
            )
        )
    }

    override suspend fun getAccounts(): List<AccountEntity> {
        listAccounts = accountDao.getAccounts().map {
            it.photoProfile?.let { s: String ->
                accountMapper.mapDbModelToEntity(it, accountMapper.mapStringToBitmap(s))
            } ?: accountMapper.mapDbModelToEntity(it, null)
        }
        return listAccounts
    }

    companion object {

        const val PREFERENCE_FILE_KEY = "PREFERENCE_FILE_KEY"
        const val EMAIL_USER_KEY = "EMAIL_USER_KEY"
        const val DEFAULT_VALUE_EMAIL = ""

    }

}