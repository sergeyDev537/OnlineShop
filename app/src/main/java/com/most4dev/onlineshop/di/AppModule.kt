package com.most4dev.onlineshop.di

import com.most4dev.onlineshop.presentation.MainViewModel
import com.most4dev.onlineshop.presentation.auth.AuthViewModel
import com.most4dev.onlineshop.presentation.home.fragments.home.HomeViewModel
import com.most4dev.onlineshop.presentation.home.fragments.itemProduct.ItemProductViewModel
import com.most4dev.onlineshop.presentation.home.fragments.profile.ProfileViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        AuthViewModel(
            application = androidApplication(),
            signInUseCase = get(),
            loginUseCase = get()
        )
    }

    viewModel {
        HomeViewModel(
            application = androidApplication(),
            getCategoryProductsUseCase = get(),
            getLatestProductsUseCase = get(),
            getFlashSaleProductsUseCase = get(),
            getBrandsUseCase = get()
        )
    }

    viewModel {
        ProfileViewModel(
            application = androidApplication(),
            getAccountUseCase = get(),
            uploadPhotoUseCase = get(),
            logoutUseCase = get()
        )
    }

    viewModel {
        ItemProductViewModel(
            application = androidApplication(),
            getProductUseCase = get()
        )
    }

    viewModel {
        MainViewModel(
            searchProductUseCase = get()
        )
    }

}