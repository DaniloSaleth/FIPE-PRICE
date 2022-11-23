package com.example.showmetheprice.ui.ano

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.showmetheprice.model.ano.Ano
import com.example.showmetheprice.repository.ano.AnoRepository
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

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

        val value = anoViewModel.ano.getOrAwaitValue()

        assertEquals(resultFromEndpoint,value)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        afterObserve: () -> Unit = {}
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data = o
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)

        try {
            afterObserve.invoke()

            // Don't wait indefinitely if the LiveData is not set.
            if (!latch.await(time, timeUnit)) {
                throw TimeoutException("LiveData value was never set.")
            }

        } finally {
            this.removeObserver(observer)
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}