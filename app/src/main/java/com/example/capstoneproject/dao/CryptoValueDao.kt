package com.example.capstoneproject.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.capstoneproject.model.CryptoValue

@Dao
interface CryptoValueDao {

    @Query("SELECT * FROM cryptoValue")
    fun getAllCryptoValues(): LiveData<List<CryptoValue>>

}