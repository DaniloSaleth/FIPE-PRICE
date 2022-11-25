package com.example.showmetheprice.repository.marcas

import com.example.showmetheprice.model.marcas.Marca
import com.example.showmetheprice.network.Endpoint
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class MarcasRepositoryImplTest{

    private val endpoint : Endpoint = mockk(relaxed = true)

    private lateinit var marcasRepository : MarcasRepository

    @Before
    fun setUp() {
        marcasRepository = MarcasRepositoryImpl(endpoint)
    }

    @Test
    fun getMarcas_ShouldReturnListOfMarcasFromEndpoint () = runBlocking{
        val expectedResultEndpoint : List<Marca> = listOf(
            Marca("", ""),
        )

        coEvery {
            endpoint.getMarcas(any())
        } returns expectedResultEndpoint

        val result = marcasRepository.getMarcas("")
        Assert.assertEquals(expectedResultEndpoint,result)
    }
}