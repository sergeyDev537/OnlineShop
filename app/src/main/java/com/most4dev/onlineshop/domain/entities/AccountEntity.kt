package com.most4dev.onlineshop.domain.entities

data class AccountEntity(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val balance: Int
)
