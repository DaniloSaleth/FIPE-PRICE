package com.example.showmetheprice.ui.modelo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.showmetheprice.TestCoroutineRule
import com.example.showmetheprice.model.modelos.Modelo
import com.example.showmetheprice.model.modelos.ModeloResponse
import com.example.showmetheprice.repository.modelos.ModelosRepository
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
internal class ModelosViewModelTest{
    @get:Rule
    val testInstantTaskExecutorRule : TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var modelosViewModel: ModelosViewModel

    @MockK
    private val modelosRepository: ModelosRepository = mockk(relaxed = true)

    @MockK
    private lateinit var modelosResponseObserver : Observer<ModelosState>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        modelosViewModel = ModelosViewModel(modelosRepository).apply {
            state.observeForever(modelosResponseObserver)
        }
    }

    @Test
    fun getModelos_onAnoSuccessMustAddListOfModeloInLiveDataState() = testCoroutineRule.runBlockingTest {
        val response = ModeloResponse(listOf(Modelo("CG 160 TITAN", "2")))
        coEvery {
            modelosRepository.getModelos(any(),any())
        } returns response

        modelosViewModel.getModelos("","")

        verifySequence {
            modelosResponseObserver.onChanged(ModelosState.ModelosSuccess(response.modelos))
        }
    }

    @Test
    fun getModelos_onEmptyMustReturnEmptyListState() = testCoroutineRule.runBlockingTest {
        val response = ModeloResponse(listOf())

        coEvery {
            modelosRepository.getModelos(any(),any())
        } returns response

        modelosViewModel.getModelos("","")

        verifySequence {
            modelosResponseObserver.onChanged(ModelosState.EmptyList)
        }
    }

    @Test
    fun getModelos_onErrorMustReturnThrowableInErrorState() = testCoroutineRule.runBlockingTest {
        //val modelo = ModeloResponse(listOf(Modelo("CG 160 TITAN", "2")))
        val response = Throwable()
        coEvery {
            modelosRepository.getModelos(any(),any())
        } throws response

        modelosViewModel.getModelos("","")

        verifySequence {
            modelosResponseObserver.onChanged(ModelosState.Error(response))
        }
    }

    @Test
    fun getModelosByName_onAnoSuccessMustAddListOfModeloInLiveDataState() = testCoroutineRule.runBlockingTest {
        val response = ModeloResponse(
            listOf(
                Modelo("CG 160 FAN", "1"),
                Modelo("CG 160 TITAN", "2")
            )
        )
        val name = "TI"
        coEvery {
            modelosRepository.getModelos(any(),any())
        } returns response

        modelosViewModel.getModelosByName("","",name)

        verifySequence {
            modelosResponseObserver.onChanged(ModelosState.ModelosSuccess(listOf(response.modelos[1])))
        }
    }

    @Test
    fun getModelosByName_onEmptyMustReturnEmptyListState() = testCoroutineRule.runBlockingTest {
        val response = ModeloResponse(
            listOf(
                Modelo("CG 160 FAN", "1"),
                //Modelo("CG 160 TITAN", "2")
            )
        )
        val name = "TI"
        coEvery {
            modelosRepository.getModelos(any(),any())
        } returns response

        modelosViewModel.getModelosByName("","",name)

        verifySequence {
            modelosResponseObserver.onChanged(ModelosState.EmptyList)
        }
    }

    @Test
    fun getModelosByName_onErrorMustReturnThrowableInErrorState() = testCoroutineRule.runBlockingTest {
        //val modelo = ModeloResponse(listOf(Modelo("CG 160 TITAN", "2")))
        val name = "TI"
        val response = Throwable()
        coEvery {
            modelosRepository.getModelos(any(),any())
        } throws response

        modelosViewModel.getModelosByName("","",name)

        verifySequence {
            modelosResponseObserver.onChanged(ModelosState.Error(response))
        }
    }

    @After
    fun tearDown() {
        modelosViewModel.state.removeObserver(modelosResponseObserver)
    }

}