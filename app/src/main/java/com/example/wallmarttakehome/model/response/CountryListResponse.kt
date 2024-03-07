package com.example.wallmartcodingchallenge.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class CountryListResponse(
    val countryList: List<CountryListResponseItem>
)