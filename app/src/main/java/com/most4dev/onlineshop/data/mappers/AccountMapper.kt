package com.most4dev.onlineshop.data.mappers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.most4dev.onlineshop.data.database.model.AccountDbModel
import com.most4dev.onlineshop.domain.entities.AccountEntity
import java.io.ByteArrayOutputStream

class AccountMapper {

    fun mapEntityToDbModel(accountEntity: AccountEntity, stringImage: String): AccountDbModel{
        return AccountDbModel(
            firstName = accountEntity.firstName,
            lastName = accountEntity.lastName,
            email = accountEntity.email,
            password = accountEntity.password,
            photoProfile = stringImage,
            balance = accountEntity.balance,
        )
    }

    fun mapDbModelToEntity(accountDbModel: AccountDbModel, bitmap: Bitmap): AccountEntity{
        return AccountEntity(
            firstName = accountDbModel.firstName,
            lastName = accountDbModel.lastName,
            email = accountDbModel.email,
            password = accountDbModel.password,
            photoProfile = bitmap,
            balance = accountDbModel.balance,
        )
    }

    fun mapBitmapToString(bitmap: Bitmap): String{
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 60, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    fun mapStringToBitmap(string: String): Bitmap{
        val byteArray = Base64.decode(string, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(
            byteArray,
            0,
            byteArray.size
        )
    }

}