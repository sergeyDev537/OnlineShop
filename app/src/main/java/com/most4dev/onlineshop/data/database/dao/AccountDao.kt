package com.most4dev.onlineshop.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.most4dev.onlineshop.data.database.model.AccountDbModel

@Dao
interface AccountDao {

    @Query("SELECT * FROM account LIMIT 1")
    fun getAccount(): LiveData<AccountDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createAccount(accountDbModel: AccountDbModel)

    @Update
    suspend fun uploadPhoto(accountDbModel: AccountDbModel)

    @Delete
    suspend fun removeUser(accountDbModel: AccountDbModel)

}