package com.example.wallmarttakehome.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wallmarttakehome.model.repository.CountryRepository

class CountryViewModelFactory(private val repository: CountryRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            CountryViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("View Model is not found")
        }
    }
}