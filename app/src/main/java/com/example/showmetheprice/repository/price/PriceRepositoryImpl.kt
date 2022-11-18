package com.example.showmetheprice.repository.price

import com.example.showmetheprice.network.Endpoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PriceRepositoryImpl (private val endpoint : Endpoint) : PriceRepository {
    override suspend fun getPrice(
        type: String,
        codigoMarca: String,
        codigoModelo: String,
        codigoAno: String,
    ): PriceRepositoryStatus {
        return withContext(Dispatchers.IO){
            try {
                val price = endpoint.getPrice(type, codigoMarca, codigoModelo, codigoAno)
                PriceRepositoryStatus.PriceSuccess(price)
            }catch (t : Throwable){
                PriceRepositoryStatus.Error(t)
            }
        }
    }

}