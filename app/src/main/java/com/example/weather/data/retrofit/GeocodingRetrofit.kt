package com.example.weather.data.retrofit

import com.example.weather.data.api.GeocodingApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://geocoding-api.open-meteo.com"

/**
 * GeocodingRetrofit - retrofit object for implementation of GeocodingApi
 */

object GeocodingRetrofit {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val geocodingApi: GeocodingApi = retrofit.create(
        GeocodingApi::class.java
    )
}