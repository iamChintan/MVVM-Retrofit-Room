package com.mvvm.retrofit.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.retrofit.api.IQuoteService
import com.mvvm.retrofit.db.QuoteDatabase
import com.mvvm.retrofit.models.QuotList
import com.mvvm.retrofit.utils.NetworkUtil

class QuoteRepository(
    private val quoteService: IQuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val qoutesLiveData = MutableLiveData<QuotList>()

    val qoutes : LiveData<QuotList>
    get() = qoutesLiveData

    suspend fun getQoute(page: Int){
        val result = quoteService.getQuote(page)

        if (NetworkUtil.isOnline(applicationContext)){
            if (result?.body() != null){
                quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                qoutesLiveData.postValue(result.body())
            }
        }else{
            val quotes =  quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuotList(1,1,1,quotes,1,1)
            qoutesLiveData.postValue(quoteList)
        }
    }
}