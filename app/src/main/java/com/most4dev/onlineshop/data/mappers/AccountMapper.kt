package com.most4dev.onlineshop.data.mappers

import com.most4dev.onlineshop.data.database.model.AccountDbModel
import com.most4dev.onlineshop.domain.entities.AccountEntity

class AccountMapper {

    fun mapEntityToDbModel(accountEntity: AccountEntity): AccountDbModel{
        return AccountDbModel(
            firstName = accountEntity.firstName,
            lastName = accountEntity.lastName,
            email = accountEntity.email,
            password = accountEntity.password,
            photoProfile = accountEntity.photoProfile,
            balance = accountEntity.balance,
        )
    }

    fun mapDbModelToEntity(accountDbModel: AccountDbModel): AccountEntity{
        return AccountEntity(
            firstName = accountDbModel.firstName,
            lastName = accountDbModel.lastName,
            email = accountDbModel.email,
            password = accountDbModel.password,
            photoProfile = accountDbModel.photoProfile,
            balance = accountDbModel.balance,
        )
    }

}