package com.example.wallmartcodingchallenge.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Language(
    @SerialName("code")
    val code: String,
    @SerialName("iso639_2")
    val iso6392: String,
    @SerialName("name")
    val name: String,
    @SerialName("nativeName")
    val nativeName: String
)