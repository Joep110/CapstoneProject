package com.example.capstoneproject.model

import com.google.gson.annotations.SerializedName

data class CryptoValues(
    @SerializedName("status")
    var status: Status,
    @SerializedName("data")
    var data: List<CryptoValue>
)


data class Status(
    @SerializedName("timestamp") var timestamp: String,
    @SerializedName("error_code") var error_code: String,
    @SerializedName("error_message") var error_message: String,
    @SerializedName("elapsed") var elapsed: Int,
    @SerializedName("credit_count") var credit_count: Int,
    @SerializedName("notice") var notice: String,
    @SerializedName("total_count") var total_count: Int
)
data class CryptoValue(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String
)