package com.example.showmetheprice.repository.ano

import com.example.showmetheprice.model.ano.Ano
import com.example.showmetheprice.network.Endpoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnoRepositoryImpl (private val endpoint : Endpoint) : AnoRepository {

    override suspend fun getAno(
        type: String,
        codigoMarca: String,
        codigoModelo: String,
    ): List<Ano> {
        return withContext(Dispatchers.IO){
            endpoint.getAno(type,codigoMarca,codigoModelo)
        }
    }

    override suspend fun getAnoByName(
        type: String,
        codigoMarca: String,
        codigoModelo: String,
        name: String,
    ): List<Ano> {
        return withContext(Dispatchers.IO) {
            endpoint.getAno(type, codigoMarca, codigoModelo)
        }
    }
}