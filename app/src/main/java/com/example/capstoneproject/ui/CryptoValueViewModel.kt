package com.example.capstoneproject.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.repository.CryptoValueRepository
import kotlinx.coroutines.launch

class CryptoValueViewModel(application: Application): AndroidViewModel(application) {

    private val cryptoValueRepository = CryptoValueRepository()

    val cryptoValues =  cryptoValueRepository.cryptoValue

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    val errorText: LiveData<String>
        get() = _errorText

    fun getLatestCryptoValues(convert: String) {
        viewModelScope.launch {
            try {
                cryptoValueRepository.getLatestCurrencies(convert)
            } catch (error: CryptoValueRepository.CryptoValueRefreshError) {
                _errorText.value = error.message
            }
        }
    }

    fun getPopularCryptoValues(convert: String) {
        viewModelScope.launch {
            try {
                cryptoValueRepository.getPopularCryptoValues(convert)
            } catch (error: CryptoValueRepository.CryptoValueRefreshError) {
                _errorText.value = error.message
            }
        }
    }
}