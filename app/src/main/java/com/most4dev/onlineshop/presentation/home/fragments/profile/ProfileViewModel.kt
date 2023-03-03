package com.most4dev.onlineshop.presentation.home.fragments.profile

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.most4dev.onlineshop.R
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
    private val logoutUseCase: LogoutUseCase,
) : AndroidViewModel(application) {

    private val context = application
    val account = getAccountUseCase(getEmail())

    private var _uploadPhotoError = MutableLiveData<String>()
    val uploadPhotoError: LiveData<String> = _uploadPhotoError

    private var _logoutError = MutableLiveData<String>()
    val logoutError: LiveData<String> = _logoutError

    private fun getEmail(): String {
        val sharedPref = context.getSharedPreferences(
            AuthRepositoryImpl.PREFERENCE_FILE_KEY,
            Context.MODE_PRIVATE
        )
        return sharedPref.getString(AuthRepositoryImpl.EMAIL_USER_KEY, DEFAULT_VALUE_EMAIL)
            ?: DEFAULT_VALUE_EMAIL
    }

    fun uploadPhoto(context: Context, uri: Uri, accountEntity: AccountEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                uploadPhotoUseCase(context, uri, accountEntity)
            } catch (e: Exception) {
                _uploadPhotoError.postValue(
                    String.format(
                        context.getString(R.string.error_upload_photo),
                        e.message
                    )
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                account.value?.let {
                    logoutUseCase(it)
                }
            } catch (e: Exception) {
                _logoutError.postValue(
                    String.format(
                        context.getString(R.string.error_logout),
                        e.message
                    )
                )
            }
        }
    }
}