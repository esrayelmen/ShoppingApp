package com.esrayelmen.e_market.data.repo

import androidx.lifecycle.LiveData
import com.esrayelmen.e_market.data.model.ProductResponse
import com.esrayelmen.e_market.data.source.local.ProductDao
import com.esrayelmen.e_market.domain.repo.CartRepo
import javax.inject.Inject

class CartRepoImpl @Inject constructor(
    private val dao: ProductDao
): CartRepo {

    override suspend fun addToCart(id: Int, isInCart: Boolean)  {
       dao.addToCart(id,isInCart)
    }

    override suspend fun getCartItems(): List<ProductResponse> {
        return dao.getCartItems()
    }



}