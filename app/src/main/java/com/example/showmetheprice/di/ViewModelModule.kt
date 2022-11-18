package com.example.showmetheprice.di

import com.example.showmetheprice.ui.ano.AnoViewModel
import com.example.showmetheprice.ui.marca.MarcasViewModel
import com.example.showmetheprice.ui.modelo.ModelosViewModel
import com.example.showmetheprice.ui.price.PriceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel {
        MarcasViewModel(get())
    }

    viewModel {
        ModelosViewModel(get())
    }

    viewModel {
        AnoViewModel(get())
    }

    viewModel {
        PriceViewModel(get())
    }
}