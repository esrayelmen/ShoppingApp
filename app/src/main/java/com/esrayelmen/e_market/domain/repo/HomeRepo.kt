package com.esrayelmen.e_market.domain.repo

import com.esrayelmen.e_market.data.model.ProductResponse
import com.esrayelmen.e_market.util.BaseResult
import com.esrayelmen.e_market.util.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepo {

    suspend fun getProducts() : Flow<BaseResult<List<ProductResponse>>>

    suspend fun searchProducts(query: String) : List<ProductResponse>

}