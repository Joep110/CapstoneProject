package com.example.capstoneproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.model.CryptoValue
import kotlinx.android.synthetic.main.item_card_crypto.view.*

class CryptoAdapter(private val cryptoValues: List<CryptoValue>) : RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun databind(game: CryptoValue) {
            itemView.tvCryptoName.text = game.name
            if (game.qoute.USD !== null) {
                itemView.tvCryptoPrice.text = game.qoute.USD.price
            } else if (game.qoute.EUR !== null) {
                itemView.tvCryptoPrice.text = game.qoute.EUR.price
            }
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