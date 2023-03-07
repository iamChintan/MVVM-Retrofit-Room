package com.mvvm.retrofit

import android.app.Application
import com.mvvm.retrofit.api.IQuoteService
import com.mvvm.retrofit.api.RetrofitHelper
import com.mvvm.retrofit.db.QuoteDatabase
import com.mvvm.retrofit.repository.QuoteRepository

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(IQuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService,database, applicationContext)
    }
}