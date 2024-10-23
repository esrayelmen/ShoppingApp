package com.esrayelmen.e_market.domain.repo

import com.esrayelmen.e_market.data.model.ProductResponse

interface FavoriteRepo {

    suspend fun getFavorites() : List<ProductResponse>

    suspend fun setFavorites(productResponse: ProductResponse)

    suspend fun isFavorite(id: Int) : Boolean
}