package com.example.showmetheprice.repository.modelos

import com.example.showmetheprice.model.modelos.ModeloResponse


interface ModelosRepository {
    suspend fun getModelos(type : String, codigoMarca : String) : ModeloResponse
    suspend fun getModelosByName(type : String, codigoMarca : String, name : String) : ModeloResponse
}