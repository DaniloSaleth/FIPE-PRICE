package com.example.showmetheprice.network

import com.example.showmetheprice.model.ano.Ano
import com.example.showmetheprice.model.marcas.Marca
import com.example.showmetheprice.model.modelos.ModeloResponse
import com.example.showmetheprice.model.price.Price
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {
    @GET("{type}/marcas")
    suspend fun getMarcas(@Path("type") type : String) : List<Marca>

    @GET("{type}/marcas/{codigoMarca}/modelos")
    suspend fun getModelos(@Path("type") type : String,
                           @Path("codigoMarca") codigoMarca : String) : ModeloResponse

    @GET("{type}/marcas/{codigoMarca}/modelos/{codigoModelo}/anos")
    suspend fun getAno(@Path("type") type : String,
                       @Path("codigoMarca") codigoMarca : String,
                       @Path("codigoModelo") codigoModelo : String) : List<Ano>

    @GET("{type}/marcas/{codigoMarca}/modelos/{codigoModelo}/anos/{codigoAno}")
    suspend fun getPrice(@Path("type") type : String,
                         @Path("codigoMarca") codigoMarca : String,
                         @Path("codigoModelo") codigoModelo : String,
                         @Path("codigoAno") codigoAno : String) : Price
}