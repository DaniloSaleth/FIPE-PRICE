package com.example.showmetheprice.repository.modelos

import com.example.showmetheprice.model.modelos.ModeloResponse
import com.example.showmetheprice.network.Endpoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ModelosRepositoryImpl (private val endpoint : Endpoint) : ModelosRepository {
    override suspend fun getModelos(type: String, codigoMarca : String): ModeloResponse {
        return withContext(Dispatchers.IO){
            endpoint.getModelos(type,codigoMarca)
        }
    }

    override suspend fun getModelosByName(type: String, codigoMarca : String, name: String): ModeloResponse {
        return withContext(Dispatchers.IO){
            endpoint.getModelos(type,codigoMarca)
        }
    }
}