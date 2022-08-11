package com.example.mvvmcoin.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.mvvmcoin.databinding.ActivityMainBinding
import com.example.mvvmcoin.model.api.CoinApi
import com.example.mvvmcoin.model.api.JobServices
import com.example.mvvmcoin.model.datamodel.AdModel
import com.example.mvvmcoin.model.datamodel.BaseModel
import com.example.mvvmcoin.view.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(): BaseViewModel() {
    var coins: MutableLiveData<List<BaseModel>>? = null

    fun init(binding: ActivityMainBinding, activity: MainActivity) {
        setViewDataBinding(binding)
        getData()
    }

    private fun getData() {
        val coinsApi = CoinApi.getRetroInstance().create(JobServices::class.java)
        var newItems: ArrayList<BaseModel>
        val adModel = AdModel("url")
        coins = MutableLiveData()
        GlobalScope.launch {
            val response = coinsApi.getCoinList()
            newItems = response.body() as ArrayList<BaseModel>
            if (newItems.size > 5) {
                newItems.add(5, adModel)
            } else {
                newItems.add(newItems.size, adModel)
            }
            coins!!.postValue(newItems)
        }
    }
}