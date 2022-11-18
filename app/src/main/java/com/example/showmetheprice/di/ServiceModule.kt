package com.example.showmetheprice.di

import com.example.showmetheprice.Constants.BASE_URL
import com.example.showmetheprice.network.Endpoint
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val serviceModule = module {
    single( named("BASE_URL")){
        BASE_URL
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(Endpoint::class.java)
    }
}