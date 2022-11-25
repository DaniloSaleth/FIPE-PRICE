package com.example.showmetheprice.repository.price


import com.example.showmetheprice.model.price.Price
import com.example.showmetheprice.network.Endpoint
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class PriceRepositoryImplTest{

    private val endpoint : Endpoint = mockk(relaxed = true)

    private lateinit var priceRepository: PriceRepository

    @Before
    fun setUp() {
        priceRepository = PriceRepositoryImpl(endpoint)
    }

    @Test
    fun getPrice_inCaseOfSuccessShouldReturnPriceFromEndpoint ()= runBlocking{
        val expectedResultEndpoint = Price("","","",0,"","","","")

        coEvery {
            endpoint.getPrice(any(),any(),any(),any())
        } returns expectedResultEndpoint

        val result = priceRepository.getPrice("","","","")
        Assert.assertEquals(expectedResultEndpoint,result)
    }
}