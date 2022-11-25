package com.example.showmetheprice.ui.ano

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.showmetheprice.TestCoroutineRule
import com.example.showmetheprice.model.ano.Ano
import com.example.showmetheprice.repository.ano.AnoRepository
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
internal class AnoViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule : TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var anoViewModel: AnoViewModel

    @MockK
    private val anoRepository : AnoRepository = mockk(relaxed = true)

    @MockK
    private lateinit var anoResponseObserver : Observer<State>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        anoViewModel = AnoViewModel(anoRepository).apply {
            state.observeForever(anoResponseObserver)
        }
    }

    @Test
    fun getAno_onAnoSuccessMustAddListOfAnoInLiveDataState() = testCoroutineRule.runBlockingTest {
        val anos = listOf(Ano("2020","2020-0"))
        coEvery {
            anoRepository.getAno(any(),any(),any())
        } returns anos

        anoViewModel.getAno("","","")

        verifySequence {
            anoResponseObserver.onChanged(State.AnoSuccess(anos))
        }
    }

    @Test
    fun getAno_onEmptyMustReturnEmptyListState() = testCoroutineRule.runBlockingTest {
        val anos = listOf<Ano>()
        coEvery {
            anoRepository.getAno(any(),any(),any())
        } returns anos

        anoViewModel.getAno("","","")

        verifySequence {
            anoResponseObserver.onChanged(State.EmptyList)
        }
    }

    @Test
    fun getAno_onErrorMustReturnThrowableInErrorState() = testCoroutineRule.runBlockingTest {
        //val anos = listOf<Ano>(Ano("2020","2020-0"))

        val erro = Throwable()
        coEvery {
            anoRepository.getAno(any(),any(),any())
        } throws erro

        anoViewModel.getAno("","","")

        verifySequence {
            anoResponseObserver.onChanged(State.Error(erro))
        }
    }

    @Test
    fun getAnoByName_onAnoSuccessMustAddListOfAnoInLiveDataState() = testCoroutineRule.runBlockingTest {
        val anos = listOf(
            Ano("2021","2021-0"),
            Ano("2020","2020-0"),
        )
        val name = "2020"
        coEvery {
            anoRepository.getAno(any(),any(),any())
        } returns anos

        anoViewModel.getAnoByName("","","",name)

        verifySequence {
            anoResponseObserver.onChanged(State.AnoSuccess(listOf(anos[1])))
        }
    }

    @Test
    fun getAnoByName_onEmptyMustReturnEmptyListState() = testCoroutineRule.runBlockingTest {
        val anos = listOf(
            Ano("2021","2021-0"),
            Ano("2020","2020-0"),
        )
        val name = "2022"

        coEvery {
            anoRepository.getAno(any(),any(),any())
        } returns anos

        anoViewModel.getAnoByName("","","",name)

        verifySequence {
            anoResponseObserver.onChanged(State.EmptyList)
        }
    }

    @Test
    fun getAnoByName_onErrorMustReturnThrowableInErrorState() = testCoroutineRule.runBlockingTest {
        //val anos = listOf<Ano>(Ano("2020","2020-0"))

        val erro = Throwable()
        coEvery {
            anoRepository.getAno(any(),any(),any())
        } throws erro

        anoViewModel.getAno("","","")

        verifySequence {
            anoResponseObserver.onChanged(State.Error(erro))
        }
    }

    @After
    fun tearDown() {
        anoViewModel.state.removeObserver(anoResponseObserver)
    }
}