package com.example.showmetheprice.ui.price

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.showmetheprice.TestCoroutineRule
import com.example.showmetheprice.model.modelos.Modelo
import com.example.showmetheprice.model.modelos.ModeloResponse
import com.example.showmetheprice.model.price.Price
import com.example.showmetheprice.repository.modelos.ModelosRepository
import com.example.showmetheprice.repository.price.PriceRepository
import com.example.showmetheprice.ui.modelo.ModelosState
import com.example.showmetheprice.ui.modelo.ModelosViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
internal class PriceViewModelTest{
    @get:Rule
    val testInstantTaskExecutorRule : TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var priceViewModel: PriceViewModel

    @MockK
    private val priceRepository: PriceRepository = mockk(relaxed = true)

    @MockK
    private lateinit var priceResponseObserver : Observer<PriceState>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        priceViewModel = PriceViewModel(priceRepository).apply {
            state.observeForever(priceResponseObserver)
        }
    }

    @Test
    fun getPrice_onAnoSuccessMustAddPriceInLiveDataState() = testCoroutineRule.runBlockingTest {
        //val erro = Throwable()
        val response = Price("","","",2022,"","","","")
        coEvery {
            priceRepository.getPrice(any(),any(),any(),any())
        } returns response

        priceViewModel.getPrice("","","","")

        verifySequence {
            priceResponseObserver.onChanged(PriceState.PriceSuccess(response))
        }
    }

    @Test
    fun getModelos_onErrorMustReturnThrowableInErrorState() = testCoroutineRule.runBlockingTest {
        //val price = Price("","","",2022,"","","","")
        val response = Throwable()
        coEvery {
            priceRepository.getPrice(any(),any(),any(),any())
        } throws response

        priceViewModel.getPrice("","","","")

        verifySequence {
            priceResponseObserver.onChanged(PriceState.Error(response))
        }
    }

    @After
    fun tearDown() {
        priceViewModel.state.removeObserver(priceResponseObserver)
    }

}