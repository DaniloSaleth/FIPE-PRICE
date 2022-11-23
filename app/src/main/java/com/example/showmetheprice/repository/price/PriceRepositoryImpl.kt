package com.example.showmetheprice.repository.price

import com.example.showmetheprice.model.price.Price
import com.example.showmetheprice.network.Endpoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PriceRepositoryImpl(private val endpoint: Endpoint) : PriceRepository {
    override suspend fun getPrice(
        type: String,
        codigoMarca: String,
        codigoModelo: String,
        codigoAno: String,
    ): Price {
        return withContext(Dispatchers.IO) {
            endpoint.getPrice(type, codigoMarca, codigoModelo, codigoAno)
        }
    }
}