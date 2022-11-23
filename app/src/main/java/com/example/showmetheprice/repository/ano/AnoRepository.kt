package com.example.showmetheprice.repository.ano

import com.example.showmetheprice.model.ano.Ano

interface AnoRepository {
    suspend fun getAno(type : String, codigoMarca : String, codigoModelo : String) : List<Ano>
}