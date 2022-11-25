package com.example.showmetheprice.repository.ano

import com.example.showmetheprice.model.ano.Ano
import com.example.showmetheprice.network.Endpoint
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
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
    fun getAno_ShouldReturnListOfAnoFromEndpoint () = runBlocking{
        //Given
        val expectedResultEndpoint : List<Ano> = listOf(Ano("2020","2020-1"))
        coEvery {
            endpoint.getAno(any(), any(), any())
        } returns expectedResultEndpoint

        //When
        val result = anoRepository.getAno("","","")

        //Then
        assertEquals(expectedResultEndpoint,result)
    }
}