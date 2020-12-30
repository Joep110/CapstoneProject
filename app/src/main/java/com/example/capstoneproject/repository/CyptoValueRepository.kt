package com.example.capstoneproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstoneproject.api.CryptoValueApi
import com.example.capstoneproject.model.CryptoValues
import com.example.capstoneproject.service.CryptoValueApiService
import kotlinx.coroutines.withTimeout

class CryptoValueRepository {
    private val cryptoValueApiService: CryptoValueApiService = CryptoValueApi.createApi()

    private val _cryptoValue: MutableLiveData<CryptoValues> = MutableLiveData()

    val cryptoValue: LiveData<CryptoValues>
        get() = _cryptoValue

    suspend fun getLatestCurrencies(convert: String, sort: String) {
        try {
            val result = withTimeout(5_000) {
                cryptoValueApiService.getLatestCryptoValues(20, convert, sort)
            }
            _cryptoValue.value = result
        } catch (error: Throwable) {
            throw CryptoValueRefreshError("Unable to refresh crypto value", error)
        }

    }

    suspend fun getPopularCryptoValues(convert: String) {
        try {
            val result = withTimeout(5_00) {
                cryptoValueApiService.getPopularCryptoValues(20, convert)
            }
            _cryptoValue.value = result
        } catch (error: Throwable) {
            throw CryptoValueRefreshError("Unable to refresh crypto value", error)
        }
    }

    class CryptoValueRefreshError(message: String, cause: Throwable) : Throwable(message, cause)
}