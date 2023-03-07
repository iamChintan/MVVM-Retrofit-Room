package com.mvvm.retrofit.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [com.mvvm.retrofit.models.Result::class], version = 1)
abstract class QuoteDatabase : RoomDatabase(){

    abstract fun quoteDao() : IQuoteDao

    companion object{
        @Volatile
        private var INSTANCE : QuoteDatabase? = null

        fun getDatabase(context : Context) : QuoteDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,
                        QuoteDatabase::class.java,
                        "quoteDB")
                        .build()
                }
            }
            return INSTANCE!!
        }


    }
}