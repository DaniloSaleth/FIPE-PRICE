package com.example.showmetheprice.repository.ano

import com.example.showmetheprice.model.ano.Ano

sealed class AnoRepositoryStatus{
    data class AnoSuccess(val response : List<Ano>) : AnoRepositoryStatus()
    data class EmptyList(val response : Boolean) : AnoRepositoryStatus()
    data class Error(val response: Throwable) : AnoRepositoryStatus()
}

interface AnoRepository {
    suspend fun getAno(type : String, codigoMarca : String, codigoModelo : String) : AnoRepositoryStatus
    suspend fun getAnoByName(type : String, codigoMarca : String, codigoModelo : String, name : String) : AnoRepositoryStatus
}