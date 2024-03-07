package com.example.wallmarttakehome.model.remote

import com.example.wallmartcodingchallenge.model.response.CountryListResponseItem
import com.example.wallmartcodingchallenge.util.Constants.BASE_URL
import com.example.wallmartcodingchallenge.util.Constants.END_POINT
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface RetrofitService {
    @GET(END_POINT)
    suspend fun getAllCountries(): Response<List<CountryListResponseItem>>

    companion object {
        private var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}