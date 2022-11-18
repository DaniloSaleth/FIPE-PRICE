package com.example.showmetheprice.repository.modelos

import com.example.showmetheprice.network.Endpoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ModelosRepositoryImpl (private val endpoint : Endpoint) : ModelosRepository {
    override suspend fun getModelos(type: String, codigoMarca : String): ModelosRepositoryStatus {
        return withContext(Dispatchers.IO){
            try {
                val listOfModelos = endpoint.getModelos(type,codigoMarca).modelos
                if (listOfModelos.isEmpty()){
                    ModelosRepositoryStatus.EmptyList(true)
                } else {
                    ModelosRepositoryStatus.ModelosSuccess(listOfModelos)
                }
            }catch (t : Throwable){
                ModelosRepositoryStatus.Error(t)
            }
        }
    }

    override suspend fun getModelosByName(type: String, codigoMarca : String, name: String): ModelosRepositoryStatus {
        return withContext(Dispatchers.IO){
            try {
                val listOfModelos= endpoint.getModelos(type,codigoMarca).modelos
                val response = listOfModelos.filter { it.nome.lowercase().contains(name.lowercase()) }
                if (response.isEmpty()){
                    ModelosRepositoryStatus.EmptyList(true)
                } else {
                    ModelosRepositoryStatus.ModelosSuccess(response)
                }
            }catch (t : Throwable){
                ModelosRepositoryStatus.Error(t)
            }
        }
    }
}