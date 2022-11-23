package com.example.showmetheprice.ui.ano

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showmetheprice.model.ano.Ano
import com.example.showmetheprice.repository.ano.AnoRepository
import kotlinx.coroutines.launch

class AnoViewModel(private val anoRepository: AnoRepository) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state : LiveData<State>
        get() = _state

    fun getAno(type : String,
               codigoMarca : String,
               codigoModelo : String) = viewModelScope.launch {
        try {
            val listOfAno = anoRepository.getAno(type,codigoMarca,codigoModelo)
            if (listOfAno.isEmpty()){
                _state.value = State.EmptyList
            } else {
                _state.value = State.AnoSuccess(listOfAno)
            }
        }catch (t : Throwable){
            _state.value = State.Error(t)
        }
    }

    fun getModelosByName(type : String,
                         codigoMarca : String,
                         codigoModelo : String,
                         name : String) = viewModelScope.launch {
        try {
            val listOfAno= anoRepository.getAno(type,codigoMarca,codigoModelo)
            val response = listOfAno.filter { it.nome.lowercase().contains(name.lowercase()) }
            if (response.isEmpty()){
                _state.value = State.EmptyList
            } else {
                _state.value = State.AnoSuccess(response)
            }
        }catch (t : Throwable){
            _state.value = State.Error(t)
        }
    }
}

sealed interface State{
    data class AnoSuccess(val response : List<Ano>) : State
    object EmptyList : State
    data class Error(val response: Throwable) : State
}