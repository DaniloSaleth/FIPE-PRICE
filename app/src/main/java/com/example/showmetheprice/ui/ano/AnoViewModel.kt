package com.example.showmetheprice.ui.ano

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showmetheprice.model.ano.Ano
import com.example.showmetheprice.repository.ano.AnoRepository
import com.example.showmetheprice.repository.ano.AnoRepositoryStatus
import kotlinx.coroutines.launch

class AnoViewModel(private val anoRepository: AnoRepository) : ViewModel() {

    private val _ano = MutableLiveData<List<Ano>>()
    val ano : LiveData<List<Ano>>
        get() = _ano

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    private val _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean>
        get() = _empty

    fun getAno(type : String,
               codigoMarca : String,
               codigoModelo : String) = viewModelScope.launch {
        anoRepository.getAno(type,codigoMarca,codigoModelo).apply {
            when(this){
                is AnoRepositoryStatus.AnoSuccess -> _ano.value = response
                is AnoRepositoryStatus.Error -> _error.value = response
                is AnoRepositoryStatus.EmptyList -> _empty.value = response
            }
        }
    }

    fun getModelosByName(type : String,
                         codigoMarca : String,
                         codigoModelo : String,
                         name : String) = viewModelScope.launch {
        anoRepository.getAnoByName(type,codigoMarca, codigoModelo, name).apply {
            when(this){
                is AnoRepositoryStatus.AnoSuccess -> _ano.value = response
                is AnoRepositoryStatus.Error -> _error.value = response
                is AnoRepositoryStatus.EmptyList -> _empty.value = response
            }
        }
    }

}