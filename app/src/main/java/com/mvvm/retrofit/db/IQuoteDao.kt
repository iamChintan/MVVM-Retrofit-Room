package com.mvvm.retrofit.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mvvm.retrofit.models.Result

@Dao
interface IQuoteDao {

    @Insert
    suspend fun addQuotes(quotes : List<Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes() : List<Result>

}