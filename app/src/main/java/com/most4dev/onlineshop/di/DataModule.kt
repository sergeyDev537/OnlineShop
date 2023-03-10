package com.most4dev.onlineshop.di

import com.most4dev.onlineshop.data.database.model.AppDatabase
import com.most4dev.onlineshop.data.impl.AuthRepositoryImpl
import com.most4dev.onlineshop.data.impl.ProductRepositoryImpl
import com.most4dev.onlineshop.data.impl.ProfileRepositoryImpl
import com.most4dev.onlineshop.data.mappers.AccountMapper
import com.most4dev.onlineshop.data.mappers.ProductMapper
import com.most4dev.onlineshop.data.network.api.ApiFactory
import com.most4dev.onlineshop.domain.repositories.AuthRepository
import com.most4dev.onlineshop.domain.repositories.ProductRepository
import com.most4dev.onlineshop.domain.repositories.ProfileRepository
import org.koin.dsl.module

val dataModule = module {

    single {
        AppDatabase.getInstance(application = get())
    }

    single {
        ApiFactory.apiService
    }

    single {
        val database = get<AppDatabase>()
        database.accountDao()
    }

    single {
        AccountMapper()
    }

    single {
        ProductMapper()
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            context = get(),
            accountDao = get(),
            accountMapper = get(),
        )
    }

    single<ProductRepository> {
        ProductRepositoryImpl(
            context = get(),
            productsApi = get(),
            productMapper = get()
        )
    }

    single<ProfileRepository> {
        ProfileRepositoryImpl(
            accountDao = get(),
            accountMapper = get()
        )
    }

}