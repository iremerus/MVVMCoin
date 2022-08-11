package com.example.mvvmcoin.model.api

import com.example.mvvmcoin.interfaces.Constant
import com.example.mvvmcoin.model.datamodel.CoinModel
import retrofit2.Response
import retrofit2.http.GET

interface JobServices {
    @GET(Constant.COINS_URL)
    suspend fun getCoinList(): Response<List<CoinModel>>
}