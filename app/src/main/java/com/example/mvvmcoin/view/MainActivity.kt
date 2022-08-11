package com.example.mvvmcoin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmcoin.databinding.ActivityMainBinding
import com.example.mvvmcoin.model.datamodel.BaseModel
import com.example.mvvmcoin.model.network.Network
import com.example.mvvmcoin.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    private lateinit var coinAdapter: CoinAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        coinAdapter = CoinAdapter(mutableListOf(), this, binding.rvCoins)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.init(binding, this)
        binding.viewModel = viewModel
        rv_coins.adapter = coinAdapter
        if (Network.isConnected(this)) {
            observeData()
        } else {
            CustomDialogFragment().show(supportFragmentManager, "customDialog")
        }

    }

    private fun getAdapter(): CoinAdapter? {
        return coinAdapter
    }

    private fun observeData() {
        val observer =
            Observer<List<BaseModel>> { coins -> getAdapter()?.updateData(coins) }
        viewModel.coins?.observe(this, observer)
    }
}