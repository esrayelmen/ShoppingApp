package com.esrayelmen.e_market.data.repo

import com.esrayelmen.e_market.data.model.ProductResponse
import com.esrayelmen.e_market.data.source.local.ProductDao
import com.esrayelmen.e_market.domain.repo.FavoriteRepo
import javax.inject.Inject

class FavoriteRepoImpl @Inject constructor(
    private val dao: ProductDao
): FavoriteRepo {

    override suspend fun getFavorites(): List<ProductResponse> {
        return dao.getFavorites()
    }

    override suspend fun isFavorite(id: Int): Boolean {
        return dao.isFavorite(id)
    }

    override suspend fun setFavorites(productResponse: ProductResponse) {
        /*productResponse.isFavorite = !productResponse.isFavorite

        if (productResponse.isFavorite) {
            productResponse.id?.let { dao.addToFavorites(it) }
        } else {
            productResponse.id?.let { dao.deleteFavorite(it) }
        }

         */

        val product = productResponse.id?.let { dao.getProduct(it) }

        if (product != null) {
            if (product.isFavorite) {
                product.isFavorite = false
                dao.deleteFavorite(productResponse.id)
            } else {
                product.isFavorite = true
                dao.addToFavorites(productResponse.id)

            }
        }


    }




}