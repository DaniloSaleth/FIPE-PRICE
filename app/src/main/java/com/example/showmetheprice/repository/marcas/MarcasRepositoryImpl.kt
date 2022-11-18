package com.example.showmetheprice.repository.marcas

import com.example.showmetheprice.network.Endpoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarcasRepositoryImpl (private val endpoint : Endpoint) : MarcasRepository {
    override suspend fun getMarcas(type: String): MarcasRepositoryStatus {
        return withContext(Dispatchers.IO){
            try {
                val listOfMarcas = endpoint.getMarcas(type)
                if (listOfMarcas.isEmpty()){
                    MarcasRepositoryStatus.EmptyList(true)
                } else {
                    MarcasRepositoryStatus.MarcasSuccess(listOfMarcas)
                }
            }catch (t : Throwable){
                MarcasRepositoryStatus.Error(t)
            }
        }
    }

    override suspend fun getMarcasByName(type: String, name: String): MarcasRepositoryStatus {
        return withContext(Dispatchers.IO){
            try {
                val listOfMarcas = endpoint.getMarcas(type)
                val response = listOfMarcas.filter { it.nome.lowercase().contains(name.lowercase()) }
                if (response.isEmpty()){
                    MarcasRepositoryStatus.EmptyList(true)
                } else {
                    MarcasRepositoryStatus.MarcasSuccess(response)
                }
            }catch (t : Throwable){
                MarcasRepositoryStatus.Error(t)
            }
        }
    }
}