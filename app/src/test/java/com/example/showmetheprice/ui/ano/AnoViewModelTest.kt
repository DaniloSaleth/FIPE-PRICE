package com.example.showmetheprice.ui.ano

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.showmetheprice.model.ano.Ano
import com.example.showmetheprice.repository.ano.AnoRepository
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class AnoViewModelTest {
    @Rule @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    private val anoRepository : AnoRepository = mockk(relaxed = true)

    private lateinit var anoViewModel: AnoViewModel

    @Before
    fun setUp() {
        anoViewModel = AnoViewModel(anoRepository)
    }

    @Test
    fun getAno_onAnoSuccessMustAddListOfAnoInLiveDataAno (){
        val resultFromEndpoint : List<Ano> = listOf(
            Ano("2020","2020-1"),
        )
    }
}