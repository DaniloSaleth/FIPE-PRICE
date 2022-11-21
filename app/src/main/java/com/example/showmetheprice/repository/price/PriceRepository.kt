package com.example.showmetheprice.repository.price

import com.example.showmetheprice.model.price.Price

sealed class PriceRepositoryStatus{
    data class PriceSuccess(val response : Price) : PriceRepositoryStatus()
    data class Error(val response: Throwable) : PriceRepositoryStatus()
}

interface PriceRepository {
    suspend fun getPrice(type : String,
                         codigoMarca : String,
                         codigoModelo : String,
                         codigoAno : String) : PriceRepositoryStatus
}