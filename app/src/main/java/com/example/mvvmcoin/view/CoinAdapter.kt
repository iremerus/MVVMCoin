package com.example.mvvmcoin.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcoin.databinding.AdLayoutBinding
import com.example.mvvmcoin.databinding.CoinLayoutBinding
import com.example.mvvmcoin.model.datamodel.BaseModel
import com.example.mvvmcoin.model.datamodel.CoinModel

class CoinAdapter(
    private var coins: List<BaseModel>, private var activity: MainActivity, viewToken: RecyclerView
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VT_COIN = 0
        private const val VT_AD = 1
    }

    inner class CoinViewHolder(_binding: CoinLayoutBinding) :
        RecyclerView.ViewHolder(_binding.root) {
        private val binding: CoinLayoutBinding = _binding
        fun bind(coin: CoinModel) {
            binding.coin = coin
            binding.executePendingBindings()
            binding.tv24h.setTextColor(
                if (coin.price_change_percentage_24h.contains("-")) {
                    Color.parseColor("#FF0000")
                } else {
                    Color.parseColor("#32CD32")
                }
            )
        }
    }

    inner class AdViewHolder(_binding: AdLayoutBinding) : RecyclerView.ViewHolder(_binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VT_AD) {
            val binding =
                AdLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AdViewHolder(binding)
        } else {
            val binding =
                CoinLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            CoinViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CoinViewHolder ->  holder.bind(coins[position] as CoinModel)
            is AdViewHolder -> holder
        }
    }

    /**
     * coins: Mutable List of CryptoModel elements
     * Updates the UI according to the data that comes from API
     */
    fun updateData(coins: List<BaseModel>) {
        this.coins = coins
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return coins.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (coins[position]) {
            is CoinModel -> {
                VT_COIN
            }
            else -> {
                VT_AD
            }
        }
    }
}