package com.most4dev.onlineshop.domain.repositories

import android.graphics.Bitmap

interface ProfileRepository {

    suspend fun uploadPhoto(bitmap: Bitmap)

}