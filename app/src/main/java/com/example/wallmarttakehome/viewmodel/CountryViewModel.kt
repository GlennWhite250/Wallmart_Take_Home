package com.example.wallmarttakehome.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallmartcodingchallenge.model.response.CountryListResponseItem
import com.example.wallmarttakehome.model.repository.CountryRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryViewModel(private val countryRepository: CountryRepository): ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    val countries = MutableLiveData<List<CountryListResponseItem>>()

    private var job: Job? = null

    val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getCountries() {
     job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
         val response = countryRepository.getAllCountries()
         withContext(Dispatchers.Main) {
             if (response.isSuccessful) {
                 countries.postValue(response.body())
                 loading.value = false
             } else {
                 onError("Error: ${response.message()}")
             }
         }
     }
    }

    private fun onError(message: String) {
        _errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}