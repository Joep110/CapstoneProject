package com.example.capstoneproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstoneproject.R
import com.example.capstoneproject.model.CryptoValue
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_latest_crypto.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LatestCryptoFragment : Fragment() {
    private var cryptoValues: ArrayList<CryptoValue> = arrayListOf()
    private val cryptoAdapter = CryptoAdapter(cryptoValues) { cryptoValue: CryptoValue -> itemClicked(cryptoValue) }
    private val viewModel: CryptoValueViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latest_crypto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        percent_change_24h.isChecked = true
        refreshLayout.setOnRefreshListener {
            viewModel.getLatestCryptoValues(currencyConverter(spinner.selectedItem.toString()), getSelectedSort())
            Snackbar.make(
                    root_layout,
                    "Reloaded view",
                    Snackbar.LENGTH_SHORT
            ).show()
            observeCryptoValues()
        }
        viewModel.getLatestCryptoValues("USD", getSelectedSort())
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

                viewModel.getLatestCryptoValues(currencyConverter(selectedItem as String), getSelectedSort())
                Snackbar.make(
                        root_layout,
                        "Changed currency",
                        Snackbar.LENGTH_SHORT
                ).show()
                observeCryptoValues()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            viewModel.getLatestCryptoValues(currencyConverter(spinner.selectedItem.toString()), getSelectedSort())
        }
    }

    private fun initViews() {
        rvCryptoValues.adapter = cryptoAdapter
        rvCryptoValues.layoutManager = GridLayoutManager(context, 1)
        cryptoAdapter.notifyDataSetChanged()
        filterIcon.setOnClickListener { toggleFilters() }
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

    private fun getSelectedSort(): String {
        return if (radioGroup.checkedRadioButtonId == R.id.percent_change_24h) {
            "percent_change_24h"
        } else {
            "percent_change_7d"
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

    private fun toggleFilters() {
        val sheetBehavior = BottomSheetBehavior.from(contentLayout)
        if (sheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            filterIcon.setImageDrawable(resources.getDrawable(R.drawable.baseline_expand_more_black_18dp))
        } else {
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            filterIcon.setImageDrawable(resources.getDrawable(R.drawable.baseline_expand_less_black_18dp))
        }
    }

    private fun itemClicked(cryptoValue: CryptoValue) {
        loadBackDrop(cryptoValue)
    }

    private fun loadBackDrop(cryptoValue: CryptoValue) {
        CryptoTitle.text = cryptoValue.name
        CryptoSymbol.text = cryptoValue.symbol
        CryptoSlug.text = cryptoValue.slug
        CryptoCmsRank.text = cryptoValue.cmc_rank
        toggleFilters()
    }
}