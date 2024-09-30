package com.esrayelmen.e_market.domain.repo

import com.esrayelmen.e_market.data.model.ProductResponse

interface DetailRepo {

    suspend fun getProduct(id: Int) : ProductResponse


}