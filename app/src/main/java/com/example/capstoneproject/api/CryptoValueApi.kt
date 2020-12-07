package com.example.capstoneproject.api

import android.content.ContentValues
import android.net.Uri
import com.example.capstoneproject.service.CryptoValueApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.URISyntaxException


class CryptoValueApi {

    companion object {
        private const val baseUrl = "https://pro-api.coinmarketcap.com"

        fun createApi() : CryptoValueApiService {
            val okHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()
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