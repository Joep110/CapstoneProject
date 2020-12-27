package com.example.capstoneproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstoneproject.R
import com.example.capstoneproject.model.CryptoValue
import kotlinx.android.synthetic.main.fragment_latest_crypto.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class PopularCryptoFragment : Fragment() {
    private var cryptoValues: ArrayList<CryptoValue> = arrayListOf()

    private lateinit var cryptoAdapter: CryptoAdapter

    private val viewModel: CryptoValueViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_crypto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshLayout.setOnRefreshListener {
            viewModel.getPopularCryptoValues(currencyConverter(spinner.selectedItem.toString()))
            observeCryptoValues()
        }
        viewModel.getPopularCryptoValues("USD")
        initViews()
        this.context?.let {
            ArrayAdapter.createFromResource(
                    it,
                    R.array.currencies,
                    android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {

                val selectedItem = parent.getItemAtPosition(position)

                viewModel.getPopularCryptoValues(currencyConverter(selectedItem as String))
                observeCryptoValues()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun initViews() {
        cryptoAdapter = CryptoAdapter(cryptoValues)
        rvCryptoValues.adapter = cryptoAdapter
        rvCryptoValues.layoutManager = GridLayoutManager(context, 1)
        cryptoAdapter.notifyDataSetChanged()
        observeCryptoValues()
    }

    private fun observeCryptoValues() {
        viewModel.cryptoValues.observe(viewLifecycleOwner) { logs ->
            this.cryptoValues.clear()
            this.cryptoValues.addAll(logs.data)
            cryptoAdapter.notifyDataSetChanged()
            refreshLayout.isRefreshing = false
        }
    }

    private fun currencyConverter(selectedItem: String): String {
        var currency = "USD"
        when(selectedItem) {
            "Euro" -> {
                currency = "EUR"

            }
        }
        return currency
    }
}