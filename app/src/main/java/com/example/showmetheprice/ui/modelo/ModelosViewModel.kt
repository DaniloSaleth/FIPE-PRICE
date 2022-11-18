package com.example.showmetheprice.ui.modelo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showmetheprice.model.modelos.Modelo
import com.example.showmetheprice.repository.modelos.ModelosRepository
import com.example.showmetheprice.repository.modelos.ModelosRepositoryStatus
import kotlinx.coroutines.launch

class ModelosViewModel(private val modelosRepository: ModelosRepository) : ViewModel() {

    private val _modelo = MutableLiveData<List<Modelo>>()
    val modelo : LiveData<List<Modelo>>
        get() = _modelo

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    private val _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean>
        get() = _empty

    fun getModelos(type : String, codigoMarca : String) = viewModelScope.launch {
        modelosRepository.getModelos(type,codigoMarca).apply {
            when(this){
                is ModelosRepositoryStatus.ModelosSuccess -> _modelo.value = response
                is ModelosRepositoryStatus.Error -> _error.value = response
                is ModelosRepositoryStatus.EmptyList -> _empty.value = response
            }
        }
    }

    fun getModelosByName(type : String, codigoMarca : String, name : String) = viewModelScope.launch {
        modelosRepository.getModelosByName(type,codigoMarca, name).apply {
            when(this){
                is ModelosRepositoryStatus.ModelosSuccess -> _modelo.value = response
                is ModelosRepositoryStatus.Error -> _error.value = response
                is ModelosRepositoryStatus.EmptyList -> _empty.value = response
            }
        }
    }

}