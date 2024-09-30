package com.esrayelmen.e_market.data.repo

import com.esrayelmen.e_market.data.model.ProductResponse
import com.esrayelmen.e_market.data.source.local.ProductDao
import com.esrayelmen.e_market.domain.repo.DetailRepo
import javax.inject.Inject

class DetailRepoImpl @Inject constructor(
    private val dao: ProductDao
): DetailRepo {

    override suspend fun getProduct(id: Int): ProductResponse {
        return dao.getProduct(id)
    }

}