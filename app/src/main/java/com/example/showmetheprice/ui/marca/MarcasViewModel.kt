package com.example.showmetheprice.ui.marca

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showmetheprice.model.marcas.Marca
import com.example.showmetheprice.repository.marcas.MarcasRepository
import com.example.showmetheprice.repository.marcas.MarcasRepositoryStatus
import kotlinx.coroutines.launch

class MarcasViewModel(private val marcasRepository: MarcasRepository) : ViewModel() {

    private val _marca = MutableLiveData<List<Marca>>()
    val marca : LiveData<List<Marca>>
        get() = _marca

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    private val _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean>
        get() = _empty

    fun getMarcas(type : String) = viewModelScope.launch {
        marcasRepository.getMarcas(type).apply {
            when(this){
                is MarcasRepositoryStatus.MarcasSuccess -> _marca.value = response
                is MarcasRepositoryStatus.Error -> _error.value = response
                is MarcasRepositoryStatus.EmptyList -> _empty.value = true
            }
        }
    }

    fun getMarcasByName(type : String, name : String) = viewModelScope.launch {
        marcasRepository.getMarcasByName(type, name).apply {
            when(this){
                is MarcasRepositoryStatus.MarcasSuccess -> _marca.value = response
                is MarcasRepositoryStatus.Error -> _error.value = response
                is MarcasRepositoryStatus.EmptyList -> _empty.value = true
            }
        }
    }

}