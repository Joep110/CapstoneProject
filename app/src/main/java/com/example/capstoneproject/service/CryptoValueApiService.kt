package com.example.capstoneproject.service

import com.example.capstoneproject.model.CryptoValues
import retrofit2.http.GET

interface CryptoValueApiService {

    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun getCryptoValues(): CryptoValues
}