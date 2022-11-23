package com.example.showmetheprice.repository.modelos

import com.example.showmetheprice.model.modelos.Modelo
import com.example.showmetheprice.model.modelos.ModeloResponse
import com.example.showmetheprice.network.Endpoint
import com.example.showmetheprice.ui.modelo.ModelosState
import io.mockk.every
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
    fun getModelos_inCaseOfSuccessShouldReturnListOfModelosFromEndpoint (){
        val expectedResultEndpoint = ModeloResponse(
            listOf(
                Modelo("", "")
            )
        )

        every {
            runBlocking {
                endpoint.getModelos(any(),any())
            }
        } returns expectedResultEndpoint

        runBlocking {
            val result = modelosRepository.getModelos("","")
            Assert.assertEquals(expectedResultEndpoint,result)
        }
    }
}