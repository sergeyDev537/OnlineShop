package com.most4dev.onlineshop.di

import com.most4dev.onlineshop.presentation.auth.AuthViewModel
import com.most4dev.onlineshop.presentation.home.fragments.cart.CartViewModel
import com.most4dev.onlineshop.presentation.home.fragments.chat.ChatViewModel
import com.most4dev.onlineshop.presentation.home.fragments.favorite.FavoriteViewModel
import com.most4dev.onlineshop.presentation.home.fragments.home.HomeViewModel
import com.most4dev.onlineshop.presentation.home.fragments.itemProduct.ItemProductViewModel
import com.most4dev.onlineshop.presentation.home.fragments.order.OrderViewModel
import com.most4dev.onlineshop.presentation.home.fragments.profile.ProfileViewModel
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

    viewModel<HomeViewModel> {
        HomeViewModel(
            getCategoryProductsUseCase = get(),
            getLatestProductsUseCase = get(),
            getFlashSaleProductsUseCase = get(),
            getBrandsUseCase = get()
        )
    }

    viewModel<FavoriteViewModel>{
        FavoriteViewModel()
    }

    viewModel<CartViewModel>{
        CartViewModel()
    }

    viewModel<ChatViewModel>{
        ChatViewModel()
    }

    viewModel<ProfileViewModel>{
        ProfileViewModel(
            application = get(),
            getAccountUseCase = get(),
            uploadPhotoUseCase = get(),
            logoutUseCase = get()
        )
    }

    viewModel<ItemProductViewModel>{
        ItemProductViewModel(
            getProductUseCase = get()
        )
    }

    viewModel<OrderViewModel>{
        OrderViewModel()
    }

}