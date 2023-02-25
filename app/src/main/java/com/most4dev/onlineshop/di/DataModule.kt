package com.most4dev.onlineshop.di

import android.media.Image
import androidx.room.Room
import com.most4dev.onlineshop.data.database.dao.AccountDao
import com.most4dev.onlineshop.data.database.model.AppDatabase
import com.most4dev.onlineshop.data.impl.AuthRepositoryImpl
import com.most4dev.onlineshop.data.impl.ProductRepositoryImpl
import com.most4dev.onlineshop.data.impl.ProfileRepositoryImpl
import com.most4dev.onlineshop.data.mappers.AccountMapper
import com.most4dev.onlineshop.data.mappers.ImageMapper
import com.most4dev.onlineshop.data.mappers.ProductMapper
import com.most4dev.onlineshop.data.network.api.ApiFactory
import com.most4dev.onlineshop.data.network.api.ProductsApi
import com.most4dev.onlineshop.domain.repositories.AuthRepository
import com.most4dev.onlineshop.domain.repositories.ProductRepository
import com.most4dev.onlineshop.domain.repositories.ProfileRepository
import org.koin.dsl.module

val dataModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).build()
    }

    single<ProductsApi> {
        ApiFactory.apiService
    }

    single<AccountDao> {
        val database = get<AppDatabase>()
        database.accountDao()
    }

    single<AccountMapper> {
        AccountMapper()
    }

    single<ImageMapper> {
        ImageMapper()
    }

    single<ProductMapper> {
        ProductMapper()
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
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
        ProfileRepositoryImpl()
    }

}