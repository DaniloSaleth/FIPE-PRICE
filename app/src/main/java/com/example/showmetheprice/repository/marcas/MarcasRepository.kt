package com.example.showmetheprice.repository.marcas

import com.example.showmetheprice.model.marcas.Marca

sealed class MarcasRepositoryStatus{
    data class MarcasSuccess(val response : List<Marca>) : MarcasRepositoryStatus()
    data class EmptyList(val response : Boolean) : MarcasRepositoryStatus()
    data class Error(val response: Throwable) : MarcasRepositoryStatus()
}

interface MarcasRepository {
    suspend fun getMarcas(type : String) : MarcasRepositoryStatus
    suspend fun getMarcasByName(type : String, name : String) : MarcasRepositoryStatus
}