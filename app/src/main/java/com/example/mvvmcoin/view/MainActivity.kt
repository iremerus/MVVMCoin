package com.example.mvvmcoin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmcoin.databinding.ActivityMainBinding
import com.example.mvvmcoin.model.datamodel.BaseModel
import com.example.mvvmcoin.model.network.Network
import com.example.mvvmcoin.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(){
    private lateinit var coinAdapter: CoinAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.init(binding, this)
        binding.viewModel = viewModel
        if (Network.isConnected(this)) {
            observeData()
        } else {
            CustomDialogFragment().show(supportFragmentManager, "customDialog")
        }
        setContentView(binding.root)
    }

    private fun observeData() {
        val observer =
            Observer<List<BaseModel>> { coins -> viewModel.getAdapter()?.updateData(coins) }
        viewModel.coins?.observe(this, observer)
    }
}