package com.example.showmetheprice.repository.marcas

import com.example.showmetheprice.model.marcas.Marca

interface MarcasRepository {
    suspend fun getMarcas(type : String) : List<Marca>
    suspend fun getMarcasByName(type : String, name : String) : List<Marca>
}