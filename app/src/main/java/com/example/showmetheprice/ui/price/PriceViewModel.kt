package com.example.showmetheprice.ui.price

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showmetheprice.model.price.Price
import com.example.showmetheprice.repository.price.PriceRepository
import kotlinx.coroutines.launch

class PriceViewModel(private val priceRepository: PriceRepository) : ViewModel() {

    private val _state = MutableLiveData<PriceState>()
    val state: LiveData<PriceState>
        get() = _state

    fun getPrice(
        type: String,
        codigoMarca: String,
        codigoModelo: String,
        codigoAno: String,
    ) = viewModelScope.launch {
        try {
            val price = priceRepository.getPrice(type, codigoMarca, codigoModelo, codigoAno)
            _state.value = PriceState.PriceSuccess(price)
        } catch (t: Throwable) {
            _state.value = PriceState.Error(t)
        }
    }
}

sealed interface PriceState {
    data class PriceSuccess(val response: Price) : PriceState
    data class Error(val response: Throwable) : PriceState
}