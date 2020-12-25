package com.example.capstoneproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstoneproject.R
import com.example.capstoneproject.model.CryptoValue
import kotlinx.android.synthetic.main.fragment_latest_crypto.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LatestCryptoFragment : Fragment() {
    private var cryptoValues: ArrayList<CryptoValue> = arrayListOf()

    private lateinit var cryptoAdapter: CryptoAdapter

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
        refreshLayout.setOnRefreshListener {
            viewModel.getLatestCryptoValues()
            observeCryptoValues()
        }
        viewModel.getLatestCryptoValues()
        initViews()
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
}