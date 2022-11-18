package com.example.showmetheprice.di

import com.example.showmetheprice.repository.ano.AnoRepository
import com.example.showmetheprice.repository.marcas.MarcasRepository
import com.example.showmetheprice.repository.ano.AnoRepositoryImpl
import com.example.showmetheprice.repository.marcas.MarcasRepositoryImpl
import com.example.showmetheprice.repository.modelos.ModelosRepository
import com.example.showmetheprice.repository.modelos.ModelosRepositoryImpl
import com.example.showmetheprice.repository.price.PriceRepository
import com.example.showmetheprice.repository.price.PriceRepositoryImpl
import org.koin.dsl.module

val repository = module {
    single<MarcasRepository> {
        MarcasRepositoryImpl(get())
    }

    single<ModelosRepository> {
        ModelosRepositoryImpl(get())
    }

    single<AnoRepository> {
        AnoRepositoryImpl(get())
    }

    single<PriceRepository> {
        PriceRepositoryImpl(get())
    }
}