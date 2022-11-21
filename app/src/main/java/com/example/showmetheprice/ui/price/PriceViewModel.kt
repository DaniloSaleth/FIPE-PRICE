package com.example.showmetheprice.ui.price

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showmetheprice.model.price.Price
import com.example.showmetheprice.repository.price.PriceRepository
import com.example.showmetheprice.repository.price.PriceRepositoryStatus
import kotlinx.coroutines.launch

class PriceViewModel(private val priceRepository: PriceRepository) : ViewModel() {

    private val _price = MutableLiveData<Price>()
    val price : LiveData<Price>
        get() = _price

    private val _iconCarro = MutableLiveData<Boolean>()
    val iconCarro : LiveData<Boolean>
        get() = _iconCarro

    private val _iconMoto = MutableLiveData<Boolean>()
    val iconMoto : LiveData<Boolean>
        get() = _iconMoto

    private val _iconCaminhao = MutableLiveData<Boolean>()
    val iconCaminhao : LiveData<Boolean>
        get() = _iconCaminhao

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    private val _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean>
        get() = _empty

    fun getPrice(type : String,
                codigoMarca : String,
                codigoModelo : String,
                codigoAno : String) = viewModelScope.launch {
        priceRepository.getPrice(type,codigoMarca,codigoModelo,codigoAno).apply {
            when(this){
                is PriceRepositoryStatus.PriceSuccess -> _price.value = response
                is PriceRepositoryStatus.Error -> _error.value = response
            }
        }
    }

    fun getIcon(type : String){
        if(type.equals("motos")){
            _iconMoto.value = true
        } else if (type.equals("carros")){
            _iconCarro.value = true
        } else {
            _iconCaminhao.value = true
        }
    }
}