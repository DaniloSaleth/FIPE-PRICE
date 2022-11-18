package com.example.showmetheprice.repository.modelos

import com.example.showmetheprice.model.modelos.Modelo

sealed class ModelosRepositoryStatus{
    data class ModelosSuccess(val response : List<Modelo>) : ModelosRepositoryStatus()
    data class EmptyList(val response : Boolean) : ModelosRepositoryStatus()
    data class Error(val response: Throwable) : ModelosRepositoryStatus()
}

interface ModelosRepository {
    suspend fun getModelos(type : String, codigoMarca : String) : ModelosRepositoryStatus
    suspend fun getModelosByName(type : String, codigoMarca : String, name : String) : ModelosRepositoryStatus
}