package com.most4dev.onlineshop.di

import com.most4dev.onlineshop.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetBrandsUseCase(productRepository = get())
    }

    factory {
        GetCategoryProductsUseCase(productRepository = get())
    }

    factory {
        GetFlashSaleProductsUseCase(productRepository = get())
    }

    factory {
        GetLatestProductsUseCase(productRepository = get())
    }

    factory {
        GetProductUseCase(productRepository = get())
    }

    factory {
        LoginUseCase(authRepository = get())
    }

    factory {
        LogoutUseCase(authRepository = get())
    }

    factory {
        SearchProductUseCase(productRepository = get())
    }

    factory {
        SignInUseCase(authRepository = get())
    }

    factory {
        UploadPhotoUseCase(profileRepository = get())
    }

    factory {
        GetAccountsUseCase(authRepository = get())
    }

    factory {
        GetAccountUseCase(profileRepository = get())
    }

}