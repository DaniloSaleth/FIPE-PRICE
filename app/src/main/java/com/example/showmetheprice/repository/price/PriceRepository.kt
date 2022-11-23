package com.example.showmetheprice.repository.price

import com.example.showmetheprice.model.price.Price

interface PriceRepository {
    suspend fun getPrice(type : String,
                         codigoMarca : String,
                         codigoModelo : String,
                         codigoAno : String) : Price
}