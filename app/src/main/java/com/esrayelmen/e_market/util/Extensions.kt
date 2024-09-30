package com.esrayelmen.e_market.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.esrayelmen.e_market.R

object Extensions {

    fun ImageView.loadImages(url: String?) {
        val options = RequestOptions()
            .placeholder(R.color.white)
            .error(R.drawable.baseline_error_24)

        Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(url)
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view: ImageView, url: String?) {
        url?.let {
            view.loadImages(url)
        }
    }
}