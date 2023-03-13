package com.mvvm.retrofit

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.retrofit.adaptor.ProgrammingAdaptor
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

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adaptor = ProgrammingAdaptor();
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adaptor

        val repository = (application as QuoteApplication).quoteRepository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
        
        mainViewModel.quote.observe(this, Observer {
            Toast.makeText(this@MainActivity, it.results.size.toString(),Toast.LENGTH_SHORT).show()
            adaptor.submitList(it.results)
        })


    }
}