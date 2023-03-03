package com.most4dev.onlineshop.data.impl

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.most4dev.onlineshop.data.database.dao.AccountDao
import com.most4dev.onlineshop.data.mappers.AccountMapper
import com.most4dev.onlineshop.domain.entities.AccountEntity
import com.most4dev.onlineshop.domain.repositories.ProfileRepository

class ProfileRepositoryImpl(
    private val accountDao: AccountDao,
    private val accountMapper: AccountMapper,
) : ProfileRepository {
    override fun getDataAccount(email: String): LiveData<AccountEntity> {
        return accountDao.checkEmail(email).map {
            accountMapper.mapDbModelToEntity(it, accountMapper.mapStringToBitmap(it.photoProfile))
        }
    }


    override suspend fun uploadPhoto(context: Context, uri: Uri, accountEntity: AccountEntity) {
        val bitmap = getBitmap(context, uri)
        val stringImage = accountMapper.mapBitmapToString(bitmap)
        accountEntity.photoProfile = accountMapper.mapStringToBitmap(stringImage)
        accountDao.uploadPhoto(
            accountMapper.mapEntityToDbModel(
                accountEntity,
                accountMapper.mapBitmapToString(accountEntity.photoProfile)
            )
        )
    }

    private fun getBitmap(context: Context, uri: Uri): Bitmap {
        val filePath = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor =
            context.contentResolver.query(
                uri,
                filePath,
                null,
                null,
                null
            )!!
        cursor.moveToFirst()
        val columnIndex: Int =
            cursor.getColumnIndex(filePath[0])
        val picturePath: String =
            cursor.getString(columnIndex)
        cursor.close()
        var thumbnail =
            BitmapFactory.decodeFile(picturePath)
        thumbnail =
            getResizedBitmap(thumbnail, 400)
        return thumbnail
    }

    private fun getResizedBitmap(
        image: Bitmap,
        maxSize: Int,
    ): Bitmap? {
        var width = image.width
        var height = image.height
        val bitmapRatio =
            width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(
            image,
            width,
            height,
            true
        )
    }

}