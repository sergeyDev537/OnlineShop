package com.most4dev.onlineshop.data.mappers

import com.most4dev.onlineshop.data.database.model.AccountDbModel
import com.most4dev.onlineshop.domain.entities.AccountEntity

class AccountMapper {

    fun mapEntityToDbModel(accountEntity: AccountEntity): AccountDbModel{
        return AccountDbModel(
            accountEntity.id,
            accountEntity.firstName,
            accountEntity.lastName,
            accountEntity.email,
            accountEntity.password,
            accountEntity.photoProfile,
            accountEntity.balance,
        )
    }

    fun mapDbModelToEntity(accountDbModel: AccountDbModel): AccountEntity{
        return AccountEntity(
            accountDbModel.id,
            accountDbModel.firstName,
            accountDbModel.lastName,
            accountDbModel.email,
            accountDbModel.password,
            accountDbModel.photoProfile,
            accountDbModel.balance,
        )
    }

}