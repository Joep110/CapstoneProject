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

    fun getPopularCryptoValues() {
        viewModelScope.launch {
            try {
                cryptoValueRepository.getAllCurrencies()
            } catch (error: CryptoValueRepository.CryptoValueRefreshError) {
                _errorText.value = error.message
            }
        }
    }
}