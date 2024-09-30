package com.esrayelmen.e_market.data.repo

import com.esrayelmen.e_market.data.model.ProductResponse
import com.esrayelmen.e_market.data.source.local.ProductDao
import com.esrayelmen.e_market.data.source.remote.ProductAPI
import com.esrayelmen.e_market.domain.repo.HomeRepo
import com.esrayelmen.e_market.util.ApiResponseHandler
import com.esrayelmen.e_market.util.BaseResult
import com.esrayelmen.e_market.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(
    private val api: ProductAPI,
    private val dao: ProductDao
): HomeRepo {

    override suspend fun getProducts(): Flow<BaseResult<List<ProductResponse>>> {
        return flow {
            emit(BaseResult.Loading())
            try {
                val response = api.getProducts()
                val apiResponseHandler = ApiResponseHandler(object: ApiResponseHandler.OnResponseListener<List<ProductResponse>> {
                    override suspend fun onSuccess(response: Response<List<ProductResponse>>) {
                        emit(BaseResult.Success(response.body()))
                    }

                    override suspend fun onClientError(errorMessage: String?) {
                        TODO("Not yet implemented")
                    }

                    override suspend fun onServerError(errorMessage: String?) {
                        TODO("Not yet implemented")
                    }

                })
                apiResponseHandler.handleResponse(response)

            } catch (e: Exception) {

            }
        }
    }

    override suspend fun searchProducts(query: String): List<ProductResponse> {
        return dao.searchProducts("%$query%")
    }
//dao.insertAll(*response.toTypedArray())

}