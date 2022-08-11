package com.example.mvvmcoin.model.api

import com.example.mvvmcoin.interfaces.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinApi {

    companion object {
        private var retrofit:Retrofit?=null

        fun getRetroInstance(): Retrofit {
            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()
            return retrofit!!
        }
    }
}