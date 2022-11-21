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
    fun getMarcas_inCaseOfSuccessShouldReturnListOfMarcasFromEndpoint (){
        val expectedResultEndpoint : List<Marca> = listOf(
            Marca("", ""),
        )

        every {
            runBlocking {
                endpoint.getMarcas(any())
            }
        } returns expectedResultEndpoint

        runBlocking {
            val expectedResult = MarcasRepositoryStatus.MarcasSuccess(expectedResultEndpoint)
            val result = marcasRepository.getMarcas("")
            Assert.assertEquals(expectedResult,result)
        }
    }

    @Test
    fun getMarcas_inCaseOfEmptyShouldReturnTrueInEmptyState(){
        val expectedResultEndpoint : List<Marca> = listOf()

        every {
            runBlocking {
                endpoint.getMarcas(any())
            }
        } returns expectedResultEndpoint

        runBlocking {
            val expectedResult = MarcasRepositoryStatus.EmptyList(true)
            val result = marcasRepository.getMarcas("")
            Assert.assertEquals(expectedResult,result)
        }
    }

    @Test
    fun getMarcas_inCaseOfErrorShouldReturnThrowable (){
        val expectedResultEndpoint = Throwable()

        every {
            runBlocking {
                endpoint.getMarcas(any())
            }
        } throws  expectedResultEndpoint

        runBlocking {
            val expectedResult = MarcasRepositoryStatus.Error(expectedResultEndpoint)
            val result = marcasRepository.getMarcas("")
            Assert.assertEquals(expectedResult,result)
        }
    }

    @Test
    fun getMarcasByName_inCaseOfSuccessShouldReturnListOfMarcasFromEndpoint (){
        val expectedResultEndpoint : List<Marca> = listOf(
            Marca("", ""),
            Marca("1", "1"),
        )

        every {
            runBlocking {
                endpoint.getMarcas(any())
            }
        } returns expectedResultEndpoint

        runBlocking {
            val expectedResult = MarcasRepositoryStatus.MarcasSuccess(listOf(expectedResultEndpoint[1]))
            val result = marcasRepository.getMarcasByName("","1")
            Assert.assertEquals(expectedResult,result)
        }
    }

    @Test
    fun getMarcasByName_inCaseOfEmptyShouldReturnTrueInEmptyState(){
        val expectedResultEndpoint : List<Marca> = listOf(
            Marca("", ""),
        )

        every {
            runBlocking {
                endpoint.getMarcas(any())
            }
        } returns expectedResultEndpoint

        runBlocking {
            val expectedResult = MarcasRepositoryStatus.EmptyList(true)
            val result = marcasRepository.getMarcasByName("","1")
            Assert.assertEquals(expectedResult,result)
        }
    }

    @Test
    fun getMarcasByName_inCaseOfErrorShouldReturnThrowable (){
        val expectedResultEndpoint = Throwable()

        every {
            runBlocking {
                endpoint.getMarcas(any())
            }
        } throws  expectedResultEndpoint

        runBlocking {
            val expectedResult = MarcasRepositoryStatus.Error(expectedResultEndpoint)
            val result = marcasRepository.getMarcasByName("","1")
            Assert.assertEquals(expectedResult,result)
        }
    }
}