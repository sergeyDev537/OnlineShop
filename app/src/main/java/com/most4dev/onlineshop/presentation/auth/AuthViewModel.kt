package com.most4dev.onlineshop.presentation.auth

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.domain.entities.AccountEntity
import com.most4dev.onlineshop.domain.usecases.LoginUseCase
import com.most4dev.onlineshop.domain.usecases.SignInUseCase
import kotlinx.coroutines.launch

class AuthViewModel(
    application: Application,
    private val signInUseCase: SignInUseCase,
    private val loginUseCase: LoginUseCase
) : AndroidViewModel(application) {

    private val context = application

    private var _signIn = MutableLiveData<Unit>()
    val signIn: LiveData<Unit> = _signIn

    private val _signInError = MutableLiveData<String>()
    val signInError: LiveData<String> = _signInError

    private val _firstNameError = MutableLiveData<String>()
    val firstNameError: LiveData<String> = _firstNameError

    private val _lastNameError = MutableLiveData<String>()
    val lastNameError: LiveData<String> = _lastNameError

    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> = _passwordError

    private val _validDataSignIn = MutableLiveData<Boolean>()
    val validDataSignIn: LiveData<Boolean> = _validDataSignIn

    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = _login

    private val _firstNameLogInError = MutableLiveData<String>()
    val firstNameLogInError: LiveData<String> = _firstNameLogInError

    private val _passwordLogInError = MutableLiveData<String>()
    val passwordLogInError: LiveData<String> = _passwordLogInError

    private val _validDataLogIn = MutableLiveData<Boolean>()
    val validDataLogIn: LiveData<Boolean> = _validDataLogIn

    fun signIn(accountEntity: AccountEntity) {
        viewModelScope.launch {
            if (signInUseCase(accountEntity)){
                _signIn.value = Unit
            }
            else{
                _signInError.value = context.getString(R.string.user_already_exists)
            }
        }

    }

    fun validateSignIn(firstName: String, lastName: String, email: String, password: String) {
        if (firstName.isBlank()) {
            _firstNameError.value = context.getString(R.string.first_name_empty)
        } else {
            _firstNameError.value = EMPTY_STRING
        }
        if (lastName.isBlank()) {
            _lastNameError.value = context.getString(R.string.last_name_empty)
        } else {
            _lastNameError.value = EMPTY_STRING
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailError.value = context.getString(R.string.email_not_valid)
        } else {
            _emailError.value = EMPTY_STRING
        }
        if (password.isBlank()) {
            _passwordError.value = context.getString(R.string.password_not_valid)
        } else {
            _passwordError.value = EMPTY_STRING
        }
        _validDataSignIn.value = firstName.isNotBlank() &&
                lastName.isNotBlank() &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                password.isNotBlank()

    }

    fun validateLogIn(firstName: String, password: String){
        if (firstName.isBlank()) {
            _firstNameLogInError.value = context.getString(R.string.first_name_empty)
        } else {
            _firstNameLogInError.value = EMPTY_STRING
        }
        if (password.isBlank()) {
            _passwordLogInError.value = context.getString(R.string.password_not_valid)
        } else {
            _passwordLogInError.value = EMPTY_STRING
        }
        _validDataLogIn.value = firstName.isNotBlank() &&
                password.isNotBlank()
    }

    fun login(firstName: String, password: String){
        viewModelScope.launch {
            _login.value = loginUseCase(firstName, password) ?: false
        }
    }

    companion object {

        const val EMPTY_STRING = ""

    }

}