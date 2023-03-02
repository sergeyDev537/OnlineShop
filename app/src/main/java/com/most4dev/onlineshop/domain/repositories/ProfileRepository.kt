package com.most4dev.onlineshop.domain.repositories

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import com.most4dev.onlineshop.domain.entities.AccountEntity

interface ProfileRepository {

    fun getDataAccount(email: String): LiveData<AccountEntity>
    suspend fun uploadPhoto(context: Context, uri: Uri, accountEntity: AccountEntity)

}