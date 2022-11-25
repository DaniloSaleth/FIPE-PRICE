package com.example.showmetheprice.ui.marca

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.showmetheprice.TestCoroutineRule
import com.example.showmetheprice.model.marcas.Marca
import com.example.showmetheprice.repository.marcas.MarcasRepository
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
internal class MarcasViewModelTest{

    @get:Rule
    val testInstantTaskExecutorRule : TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var marcasViewModel: MarcasViewModel

    @MockK
    private val marcasRepository: MarcasRepository = mockk(relaxed = true)

    @MockK
    private lateinit var marcasResponseObserver : Observer<MarcasState>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        marcasViewModel = MarcasViewModel(marcasRepository).apply {
            state.observeForever(marcasResponseObserver)
        }
    }

    @Test
    fun getMarcas_onAnoSuccessMustAddListOfModeloInLiveDataState() = testCoroutineRule.runBlockingTest {
        val response = listOf(Marca("HONDA","1"))
        coEvery {
            marcasRepository.getMarcas(any())
        } returns response

        marcasViewModel.getMarcas("")

        verifySequence {
            marcasResponseObserver.onChanged(MarcasState.MarcasSuccess(response))
        }
    }

    @Test
    fun getMarcas_onEmptyMustReturnEmptyListState() = testCoroutineRule.runBlockingTest {
        val response = listOf<Marca>()
        coEvery {
            marcasRepository.getMarcas(any())
        } returns response

        marcasViewModel.getMarcas("")

        verifySequence {
            marcasResponseObserver.onChanged(MarcasState.EmptyList)
        }
    }

    @Test
    fun getMarcas_onErrorMustReturnThrowableInErrorState() = testCoroutineRule.runBlockingTest {
        //val marcas = listOf(Marca("HONDA","1"))
        val name = "Au"
        val response = Throwable()
        coEvery {
            marcasRepository.getMarcas(any())
        } throws response

        marcasViewModel.getMarcas("")

        verifySequence {
            marcasResponseObserver.onChanged(MarcasState.Error(response))
        }
    }

    @Test
    fun getMarcasByName_onAnoSuccessMustAddListOfMarcaInLiveDataState() = testCoroutineRule.runBlockingTest {
        val response = listOf(
            Marca("HONDA","1"),
            Marca("AUDI","2")
        )
        val name = "Au"
        coEvery {
            marcasRepository.getMarcas(any())
        } returns response

        marcasViewModel.getMarcasByName("",name)

        verifySequence {
            marcasResponseObserver.onChanged(MarcasState.MarcasSuccess(listOf(response[1])))
        }
    }

    @Test
    fun getMarcasByName_onEmptyMustReturnEmptyListState() = testCoroutineRule.runBlockingTest {
        val response = listOf(
            Marca("HONDA","1"),
            //Marca("AUDI","2"),
            Marca("FIAT","3")
        )
        val name = "Au"

        coEvery {
            marcasRepository.getMarcas(any())
        } returns response

        marcasViewModel.getMarcasByName("",name)

        verifySequence {
            marcasResponseObserver.onChanged(MarcasState.EmptyList)
        }
    }

    @Test
    fun getMarcasByName_onErrorMustReturnThrowableInErrorState() = testCoroutineRule.runBlockingTest {
        //val marcas = listOf(Marca("HONDA","1"))
        val name = "Au"
        val response = Throwable()
        coEvery {
            marcasRepository.getMarcas(any())
        } throws  response

        marcasViewModel.getMarcasByName("", name)

        verifySequence {
            marcasResponseObserver.onChanged(MarcasState.Error(response))
        }
    }

    @After
    fun tearDown() {
        marcasViewModel.state.removeObserver(marcasResponseObserver)
    }

}