package com.example.showmetheprice.ui.marca

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showmetheprice.model.marcas.Marca
import com.example.showmetheprice.repository.marcas.MarcasRepository
import kotlinx.coroutines.launch

class MarcasViewModel(private val marcasRepository: MarcasRepository) : ViewModel() {

    private val _state = MutableLiveData<MarcasState>()
    val state : LiveData<MarcasState>
        get() = _state

    fun getMarcas(type : String) = viewModelScope.launch {
        try {
            marcasRepository.getMarcas(type).apply {
                val listOfMarcas = marcasRepository.getMarcas(type)
                if (listOfMarcas.isEmpty()){
                    _state.value = MarcasState.EmptyList
                } else {
                    _state.value = MarcasState.MarcasSuccess(listOfMarcas)
                }
            }
        }catch (t : Throwable){
            _state.value = MarcasState.Error(t)
        }
    }

    fun getMarcasByName(type : String, name : String) = viewModelScope.launch {
        try {
            val listOfMarcas = marcasRepository.getMarcas(type)
            val response = listOfMarcas.filter { it.nome.lowercase().contains(name.lowercase()) }
            if (response.isEmpty()){
                _state.value = MarcasState.EmptyList
            } else {
                _state.value = MarcasState.MarcasSuccess(response)
            }
        }catch (t : Throwable){
            _state.value = MarcasState.Error(t)
        }
    }
}

sealed interface MarcasState{
    data class MarcasSuccess(val response : List<Marca>) : MarcasState
    object EmptyList : MarcasState
    data class Error(val response: Throwable) : MarcasState
}