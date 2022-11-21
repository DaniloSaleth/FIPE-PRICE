package com.example.showmetheprice.repository.modelos

import com.example.showmetheprice.model.modelos.Modelo
import com.example.showmetheprice.model.modelos.ModeloResponse
import com.example.showmetheprice.network.Endpoint
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
            val expectedResult = ModelosRepositoryStatus.ModelosSuccess(expectedResultEndpoint.modelos)
            val result = modelosRepository.getModelos("","")
            Assert.assertEquals(expectedResult,result)
        }
    }

    @Test
    fun getModelos_inCaseOfEmptyShouldReturnTrueInEmptyState(){
        val expectedResultEndpoint = ModeloResponse(
            listOf()
        )

        every {
            runBlocking {
                endpoint.getModelos(any(),any())
            }
        } returns expectedResultEndpoint

        runBlocking {
            val expectedResult = ModelosRepositoryStatus.EmptyList(true)
            val result = modelosRepository.getModelos("","")
            Assert.assertEquals(expectedResult,result)
        }
    }

    @Test
    fun getModelos_inCaseOfErrorShouldReturnThrowable (){
        val expectedResultEndpoint = Throwable()

        every {
            runBlocking {
                endpoint.getModelos(any(),any())
            }
        } throws  expectedResultEndpoint

        runBlocking {
            val expectedResult = ModelosRepositoryStatus.Error(expectedResultEndpoint)
            val result = modelosRepository.getModelos("","")
            Assert.assertEquals(expectedResult,result)
        }
    }

    @Test
    fun getModelosByName_inCaseOfSuccessShouldReturnListOfModelosFromEndpoint (){
        val expectedResultEndpoint = ModeloResponse(
            listOf(
                Modelo("", ""),
                Modelo("1", "1"),
            )
        )

        every {
            runBlocking {
                endpoint.getModelos(any(),any())
            }
        } returns expectedResultEndpoint

        runBlocking {
            val expectedResult = ModelosRepositoryStatus.ModelosSuccess(listOf(expectedResultEndpoint.modelos[1]))
            val result = modelosRepository.getModelosByName("","","1")
            Assert.assertEquals(expectedResult,result)
        }
    }

    @Test
    fun getMarcasByName_inCaseOfEmptyShouldReturnTrueInEmptyState(){
        val expectedResultEndpoint = ModeloResponse(
            listOf(
                Modelo("", ""),
            )
        )

        every {
            runBlocking {
                endpoint.getModelos(any(),any())
            }
        } returns expectedResultEndpoint

        runBlocking {
            val expectedResult = ModelosRepositoryStatus.EmptyList(true)
            val result = modelosRepository.getModelosByName("","","1")
            Assert.assertEquals(expectedResult,result)
        }
    }

    @Test
    fun getModelosByName_inCaseOfErrorShouldReturnThrowable (){
        val expectedResultEndpoint = Throwable()

        every {
            runBlocking {
                endpoint.getModelos(any(),any())
            }
        } throws  expectedResultEndpoint

        runBlocking {
            val expectedResult = ModelosRepositoryStatus.Error(expectedResultEndpoint)
            val result = modelosRepository.getModelosByName("","","1")
            Assert.assertEquals(expectedResult,result)
        }
    }
}