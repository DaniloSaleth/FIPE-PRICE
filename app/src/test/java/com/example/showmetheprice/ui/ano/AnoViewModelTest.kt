package com.example.showmetheprice.ui.ano

import com.example.showmetheprice.repository.ano.AnoRepository
import com.example.showmetheprice.repository.ano.AnoRepositoryStatus
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class AnoViewModelTest {

    private val anoRepository : AnoRepository = mockk(relaxed = true)

    private lateinit var anoViewModel: AnoViewModel

    @Before
    fun setUp() {
        anoViewModel = AnoViewModel(anoRepository)
    }

    @Test
    fun getAno_inCaseOfSuccessShouldChange (){
        val expectedResult = AnoRepositoryStatus.AnoSuccess(listOf())

        every {
            runBlocking {
                anoRepository.getAno(any(), any(), any())
            }
        } returns expectedResult

        anoViewModel.getAno("","","")

    }
}