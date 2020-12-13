package com.example.capstoneproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.model.CryptoValue
import java.text.SimpleDateFormat

class CryptoAdapter(private val cryptoValues: List<CryptoValue>) : RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(game: CryptoValue) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.crypto_values_backlog, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cryptoValues.size
    }

    override fun onBindViewHolder(holder: CryptoAdapter.ViewHolder, position: Int) {
        holder.databind(cryptoValues[position])
    }
}