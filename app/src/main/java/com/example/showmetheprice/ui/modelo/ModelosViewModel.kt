package com.example.showmetheprice.ui.modelo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showmetheprice.model.modelos.Modelo
import com.example.showmetheprice.repository.modelos.ModelosRepository
import kotlinx.coroutines.launch

class ModelosViewModel(private val modelosRepository: ModelosRepository) : ViewModel() {

    private val _state = MutableLiveData<ModelosState>()
    val state : LiveData<ModelosState>
        get() = _state

    fun getModelos(type : String, codigoMarca : String) = viewModelScope.launch {
        try {
            val listOfModelos = modelosRepository.getModelos(type,codigoMarca).modelos
            if (listOfModelos.isEmpty()){
                _state.value = ModelosState.EmptyList
            } else {
                _state.value = ModelosState.ModelosSuccess(listOfModelos)
            }
        }catch (t : Throwable){
            _state.value = ModelosState.Error(t)
        }
    }

    fun getModelosByName(type : String, codigoMarca : String, name : String) = viewModelScope.launch {
        try {
            val listOfModelos= modelosRepository.getModelos(type,codigoMarca).modelos
            val response = listOfModelos.filter { it.nome.lowercase().contains(name.lowercase()) }
            if (response.isEmpty()){
                _state.value = ModelosState.EmptyList
            } else {
                _state.value = ModelosState.ModelosSuccess(response)
            }
        }catch (t : Throwable){
            _state.value = ModelosState.Error(t)
        }
    }
}

sealed interface ModelosState{
    data class ModelosSuccess(val response : List<Modelo>) : ModelosState
    object EmptyList : ModelosState
    data class Error(val response: Throwable) : ModelosState
}