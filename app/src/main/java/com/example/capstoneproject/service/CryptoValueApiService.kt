package com.example.capstoneproject.service

import com.example.capstoneproject.model.CryptoValues
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoValueApiService {

    @GET("/v1/cryptocurrency/listings/latest?")
    suspend fun getLatestCryptoValues(@Query("limit") limit: Int, @Query("convert") convert: String, @Query("sort") sort: String): CryptoValues

    @GET("/v1/cryptocurrency/listings/latest?")
    suspend fun getPopularCryptoValues(@Query("limit") limit: Int, @Query("convert") convert: String): CryptoValues
}