package com.example.showmetheprice.repository.ano

import com.example.showmetheprice.model.ano.Ano
import com.example.showmetheprice.network.Endpoint
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class AnoRepositoryImplTest {

    private val endpoint : Endpoint = mockk(relaxed = true)

    private lateinit var anoRepository : AnoRepository

    @Before
    fun setUp() {
        anoRepository = AnoRepositoryImpl(endpoint)
    }

    @Test
    fun getAno_inCaseOfSuccessShouldReturnListOfAnoFromEndpoint (){
        val expectedResultEndpoint : List<Ano> = listOf(
            Ano("2020","2020-1"),
        )

        every {
            runBlocking {
                endpoint.getAno(any(), any(), any())
            }
        } returns expectedResultEndpoint

        runBlocking {
            val expectedResult = AnoRepositoryStatus.AnoSuccess(expectedResultEndpoint)
            val result = anoRepository.getAno("","","")
            Assert.assertEquals(expectedResult,result)
        }
    }

    @Test
    fun getAno_inCaseOfEmptyShouldReturnTrueInEmptyState (){
        val expectedResultEndpoint : List<Ano> = listOf()

        every {
            runBlocking {
                endpoint.getAno(any(), any(), any())
            }
        } returns expectedResultEndpoint

        runBlocking {
            val expectedResult = AnoRepositoryStatus.EmptyList(true)
            val result = anoRepository.getAno("","","")
            Assert.assertEquals(expectedResult,result)
        }
    }

    @Test
    fun getAno_inCaseOfErrorShouldReturnThrowable (){
        val expectedResultEndpoint = Throwable()

        every {
            runBlocking {
                endpoint.getAno(any(),any(),any())
            }
        } throws expectedResultEndpoint

        runBlocking {
            val expectedResult = AnoRepositoryStatus.Error(expectedResultEndpoint)
            val result = anoRepository.getAno("","","")
            Assert.assertEquals(expectedResult,result)
        }
    }

    @Test
    fun getAnoByName_inCaseOfSuccessShouldReturnListOfAnoFromEndpoint (){
        val expectedResultEndpoint : List<Ano> = listOf(
            Ano("2020","2020-1"),
            Ano("2021","2021-1"),
            Ano("2023","2023-1"),
        )

        val name = "2020"

        every {
            runBlocking {
                endpoint.getAno(any(), any(), any())
            }
        } returns expectedResultEndpoint

        runBlocking {
            val expectedResult = AnoRepositoryStatus.AnoSuccess(listOf(expectedResultEndpoint[0]))
            val result = anoRepository.getAnoByName("","","",name)
            Assert.assertEquals(expectedResult,result)
        }
    }

    @Test
    fun getAnoByName_inCaseOfEmptyShouldReturnTrueInEmptyState (){
        val expectedResultEndpoint : List<Ano> = listOf(
            //Ano("2020","2020-1"),
            Ano("2021","2021-1"),
            Ano("2023","2023-1"),
        )

        val name = "2020"

        every {
            runBlocking {
                endpoint.getAno(any(), any(), any())
            }
        } returns expectedResultEndpoint

        runBlocking {
            val expectedResult = AnoRepositoryStatus.EmptyList(true)
            val result = anoRepository.getAnoByName("","","",name)
            Assert.assertEquals(expectedResult,result)
        }
    }

    @Test
    fun getAnoByName_inCaseOfErrorShouldReturnThrowable (){
        val expectedResultEndpoint = Throwable()

            every {
            runBlocking {
                endpoint.getAno(any(),any(),any())
            }
        } throws expectedResultEndpoint

        runBlocking {
            val expectedResult = AnoRepositoryStatus.Error(expectedResultEndpoint)
            val result = anoRepository.getAnoByName("","","","0000")
            Assert.assertEquals(expectedResult,result)
        }
    }
}