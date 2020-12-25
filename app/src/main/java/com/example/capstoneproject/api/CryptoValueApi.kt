package com.example.capstoneproject.api

import com.example.capstoneproject.service.CryptoValueApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CryptoValueApi {

    companion object {
        private const val baseUrl = "https://pro-api.coinmarketcap.com"
        private val apiKey = "5e247100-80e1-4e74-99f9-3641950b61cb"

        fun createApi() : CryptoValueApiService {
            val okHttpClient = OkHttpClient.Builder().addInterceptor { chain -> chain.request().newBuilder().addHeader("X-CMC_PRO_API_KEY", apiKey).build()
                .let(chain::proceed)
            }.addInterceptor(HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)).build()

            val cryptoValueApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return cryptoValueApi.create(CryptoValueApiService::class.java)
        }
    }

}