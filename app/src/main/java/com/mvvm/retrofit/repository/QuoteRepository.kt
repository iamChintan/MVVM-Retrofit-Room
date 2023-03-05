package com.mvvm.retrofit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.retrofit.api.IQuoteService
import com.mvvm.retrofit.models.QuotList

class QuoteRepository(private val quoteService: IQuoteService) {

    private val qoutesLiveData = MutableLiveData<QuotList>()

    val qoutes : LiveData<QuotList>
    get() = qoutesLiveData

    suspend fun getQoute(page: Int){
        val result = quoteService.getQuote(page)

        if (result?.body() != null){
            qoutesLiveData.postValue(result.body())
        }
    }
}