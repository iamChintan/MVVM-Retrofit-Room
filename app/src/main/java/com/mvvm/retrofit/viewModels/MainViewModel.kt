package com.mvvm.retrofit.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.retrofit.models.QuotList
import com.mvvm.retrofit.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (private val repository: QuoteRepository) : ViewModel(){

    init {
        viewModelScope.launch (Dispatchers.IO) {
            repository.getQoute(1)
        }
    }

    val quote : LiveData<QuotList>
    get() = repository.qoutes
}