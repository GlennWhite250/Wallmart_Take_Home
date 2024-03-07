package com.example.wallmarttakehome.model.repository

import com.example.wallmarttakehome.model.remote.RetrofitService

class CountryRepository(private val retrofitService: RetrofitService) {
    suspend fun getAllCountries() = retrofitService.getAllCountries()
}