package com.esrayelmen.e_market.presentation.home

import android.view.View
import com.esrayelmen.e_market.data.model.ProductResponse

interface ProductClickListener {

    fun onProductClicked(productResponse: ProductResponse)

    fun onFavoriteClicked(productResponse: ProductResponse)

    fun onAddToCartClicked(productResponse: ProductResponse)

}