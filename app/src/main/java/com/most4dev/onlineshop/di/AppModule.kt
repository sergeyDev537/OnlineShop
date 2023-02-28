package com.most4dev.onlineshop.di

import com.most4dev.onlineshop.presentation.auth.AuthViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<AuthViewModel> {
        AuthViewModel(
            application = androidApplication(),
            signInUseCase = get(),
            loginUseCase = get()
        )
    }

}