package com.example.capstoneproject.service

import com.example.capstoneproject.model.CryptoValues
import retrofit2.http.GET

interface CryptoValueApiService {

    @GET("/v1/cryptocurrency/listings/latest?limit=20&convert=EUR")
    suspend fun getLatestCryptoValues(): CryptoValues

    @GET("/v1/cryptocurrency/listings/latest?limit=20")
    suspend fun getPopularCryptoValues(): CryptoValues
}