package com.example.showmetheprice.repository.marcas

import com.example.showmetheprice.model.marcas.Marca
import com.example.showmetheprice.network.Endpoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarcasRepositoryImpl (private val endpoint : Endpoint) : MarcasRepository {
    override suspend fun getMarcas(type: String): List<Marca> {
        return withContext(Dispatchers.IO){
                endpoint.getMarcas(type)
        }
    }
}