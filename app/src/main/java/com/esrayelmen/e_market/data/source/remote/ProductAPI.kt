package com.esrayelmen.e_market.data.source.remote

import com.esrayelmen.e_market.data.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {

    @GET("/products")
    suspend fun getProducts(): Response<List<ProductResponse>>

}