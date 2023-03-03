package com.most4dev.onlineshop.domain.usecases

import androidx.lifecycle.LiveData
import com.most4dev.onlineshop.domain.entities.AccountEntity
import com.most4dev.onlineshop.domain.repositories.ProfileRepository

class GetAccountUseCase(private val profileRepository: ProfileRepository) {

    operator fun invoke(email: String): LiveData<AccountEntity?> {
        return profileRepository.getDataAccount(email)
    }

}