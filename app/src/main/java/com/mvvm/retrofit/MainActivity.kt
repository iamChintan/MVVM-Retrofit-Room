package com.mvvm.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mvvm.retrofit.api.IQuoteService
import com.mvvm.retrofit.api.RetrofitHelper
import com.mvvm.retrofit.repository.QuoteRepository
import com.mvvm.retrofit.viewModels.MainViewModel
import com.mvvm.retrofit.viewModels.MainViewModelFactory

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quoteService = RetrofitHelper.getInstance().create(IQuoteService::class.java)
        val repository = QuoteRepository(quoteService)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
        
        mainViewModel.quote.observe(this, Observer {
            Log.d(TAG, "onCreate: " + it.results.toString())
        })
    }
}