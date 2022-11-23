package com.example.showmetheprice.repository.marcas

import com.example.showmetheprice.model.marcas.Marca
import com.example.showmetheprice.network.Endpoint
import io.mockk.every
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
    fun getMarcas_ShouldReturnListOfMarcasFromEndpoint (){
        val expectedResultEndpoint : List<Marca> = listOf(
            Marca("", ""),
        )

        every {
            runBlocking {
                endpoint.getMarcas(any())
            }
        } returns expectedResultEndpoint

        runBlocking {
            val result = marcasRepository.getMarcas("")
            Assert.assertEquals(expectedResultEndpoint,result)
        }
    }
}