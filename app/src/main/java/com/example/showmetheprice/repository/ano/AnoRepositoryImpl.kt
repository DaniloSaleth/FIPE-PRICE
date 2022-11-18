package com.example.showmetheprice.repository.ano

import com.example.showmetheprice.network.Endpoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnoRepositoryImpl (private val endpoint : Endpoint) : AnoRepository {
    override suspend fun getAno(
        type: String,
        codigoMarca: String,
        codigoModelo: String,
    ): AnoRepositoryStatus {
        return withContext(Dispatchers.IO){
            try {
                val listOfAno= endpoint.getAno(type,codigoMarca,codigoModelo)
                if (listOfAno.isEmpty()){
                    AnoRepositoryStatus.EmptyList(true)
                } else {
                    AnoRepositoryStatus.AnoSuccess(listOfAno)
                }
            }catch (t : Throwable){
                AnoRepositoryStatus.Error(t)
            }
        }
    }

    override suspend fun getAnoByName(
        type: String,
        codigoMarca: String,
        codigoModelo: String,
        name: String,
    ): AnoRepositoryStatus {
        return withContext(Dispatchers.IO){
            try {
                val listOfAno= endpoint.getAno(type,codigoMarca,codigoModelo)
                val response = listOfAno.filter { it.nome.lowercase().contains(name.lowercase()) }
                if (response.isEmpty()){
                    AnoRepositoryStatus.EmptyList(true)
                } else {
                    AnoRepositoryStatus.AnoSuccess(response)
                }
            }catch (t : Throwable){
                AnoRepositoryStatus.Error(t)
            }
        }
    }
}