package com.most4dev.onlineshop.domain.usecases

import android.content.Context
import android.net.Uri
import com.most4dev.onlineshop.domain.entities.AccountEntity
import com.most4dev.onlineshop.domain.repositories.ProfileRepository

class UploadPhotoUseCase(private val profileRepository: ProfileRepository) {

    suspend operator fun invoke(context: Context, uri: Uri, accountEntity: AccountEntity){
        profileRepository.uploadPhoto(context, uri, accountEntity)
    }

}