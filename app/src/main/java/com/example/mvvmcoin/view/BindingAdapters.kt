package com.example.mvvmcoin.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CustomBindings {
    companion object {
        @BindingAdapter("setAdapter")
        @JvmStatic
        fun bindRecyclerViewAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
            view.setHasFixedSize(true)
            view.adapter = adapter
        }
    }
}

@BindingAdapter("url")
fun setImage(imageView: ImageView, url: String) {
    Picasso.with(imageView.context).load(url).into(imageView)
}

