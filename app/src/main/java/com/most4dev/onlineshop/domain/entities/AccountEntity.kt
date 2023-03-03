package com.most4dev.onlineshop.domain.entities

import android.graphics.Bitmap

data class AccountEntity(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    var photoProfile: Bitmap?,
    val balance: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AccountEntity

        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (balance != other.balance) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + photoProfile.hashCode()
        result = 31 * result + balance
        return result
    }
}
