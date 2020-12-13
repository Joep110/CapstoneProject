package com.example.capstoneproject.model

import androidx.room.Entity
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

@Entity(tableName = "cryptoValue")
data class CryptoValue(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("symbol") var symbol: String,
    @SerializedName("slug") var slug: String,
    @SerializedName("num_market_pairs") var num_market_pairs: String,
    @SerializedName("date_added") var date_added: String,
    @SerializedName("max_supply") var max_supply: String,
    @SerializedName("circulating_supply") var circulating_supply: String,
    @SerializedName("total_supply") var total_supply: String,
    @SerializedName("cmc_rank") var cmc_rank: String,
    @SerializedName("last_updated") var last_updated: String
)

data class Tag(
    @SerializedName("name") var name: String
)
