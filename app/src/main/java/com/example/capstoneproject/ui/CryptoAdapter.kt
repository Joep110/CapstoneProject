package com.example.capstoneproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.model.CryptoValue
import kotlinx.android.synthetic.main.item_card_crypto.view.*

class CryptoAdapter(private val cryptoValues: List<CryptoValue>, private val clickListener: (CryptoValue) -> Unit) : RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun databind(cryptoValue: CryptoValue) {
            itemView.tvCryptoName.text = cryptoValue.name
            if (cryptoValue.qoute.USD !== null) {
                itemView.tvCryptoPrice.text = cryptoValue.qoute.USD.price
                itemView.tvCryptoCurrency.text = "$"
            } else if (cryptoValue.qoute.EUR !== null) {
                itemView.tvCryptoPrice.text = cryptoValue.qoute.EUR.price
                itemView.tvCryptoCurrency.text = "€"
            }
            itemView.setOnClickListener{ clickListener(cryptoValue)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_crypto, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cryptoValues.size
    }

    override fun onBindViewHolder(holder: CryptoAdapter.ViewHolder, position: Int) {
        holder.databind(cryptoValues[position])
    }
}