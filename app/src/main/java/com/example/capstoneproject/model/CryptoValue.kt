package com.example.capstoneproject.model

import com.google.gson.annotations.SerializedName

data class CryptoValue(
    @SerializedName("name") var name: String
)