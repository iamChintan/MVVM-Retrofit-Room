package com.mvvm.retrofit.api

import com.mvvm.retrofit.models.QuotList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IQuoteService {

    @GET("/quotes")
    suspend fun getQuote(@Query("page") page : Int) : Response<QuotList>
}