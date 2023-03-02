package com.most4dev.onlineshop.presentation.home.fragments.profile

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.most4dev.onlineshop.data.impl.AuthRepositoryImpl
import com.most4dev.onlineshop.data.impl.AuthRepositoryImpl.Companion.DEFAULT_VALUE_EMAIL
import com.most4dev.onlineshop.domain.entities.AccountEntity
import com.most4dev.onlineshop.domain.usecases.GetAccountUseCase
import com.most4dev.onlineshop.domain.usecases.LogoutUseCase
import com.most4dev.onlineshop.domain.usecases.UploadPhotoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(
    application: Application,
    getAccountUseCase: GetAccountUseCase,
    private val uploadPhotoUseCase: UploadPhotoUseCase,
    private val logoutUseCase: LogoutUseCase
): ViewModel() {

    private val context = application
    val account = getAccountUseCase(getEmail())

    private fun getEmail(): String {
        val sharedPref = context.getSharedPreferences(
            AuthRepositoryImpl.PREFERENCE_FILE_KEY,
            Context.MODE_PRIVATE
        )
        return sharedPref.getString(AuthRepositoryImpl.EMAIL_USER_KEY, DEFAULT_VALUE_EMAIL) ?: DEFAULT_VALUE_EMAIL
    }

    fun uploadPhoto(context: Context, uri: Uri, accountEntity: AccountEntity){
        viewModelScope.launch(Dispatchers.IO) {
            uploadPhotoUseCase(context, uri, accountEntity)
        }
    }

    fun logout(){
        viewModelScope.launch(Dispatchers.IO) {
            logoutUseCase(account.value!!)
        }
    }

}