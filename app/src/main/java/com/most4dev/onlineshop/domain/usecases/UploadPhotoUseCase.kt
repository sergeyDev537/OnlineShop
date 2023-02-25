package com.most4dev.onlineshop.domain.usecases

import android.graphics.Bitmap
import com.most4dev.onlineshop.domain.repositories.ProfileRepository

class UploadPhotoUseCase(private val profileRepository: ProfileRepository) {

    suspend operator fun invoke(bitmap: Bitmap){
        profileRepository.uploadPhoto(bitmap)
    }

}