package com.example.showmetheprice.repository.modelos

import com.example.showmetheprice.model.modelos.Modelo
import com.example.showmetheprice.model.modelos.ModeloResponse
import com.example.showmetheprice.network.Endpoint
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


internal class ModelosRepositoryImplTest {

    private val endpoint : Endpoint = mockk(relaxed = true)

    private lateinit var modelosRepository: ModelosRepository

    @Before
    fun setUp() {
        modelosRepository = ModelosRepositoryImpl(endpoint)
    }

    @Test
    fun getModelos_inCaseOfSuccessShouldReturnListOfModelosFromEndpoint () = runBlocking{
        val expectedResultEndpoint = ModeloResponse(
            listOf(
                Modelo("", "")
            )
        )

        coEvery {
            endpoint.getModelos(any(),any())
        } returns expectedResultEndpoint

        val result = modelosRepository.getModelos("","")
        Assert.assertEquals(expectedResultEndpoint,result)
    }
}