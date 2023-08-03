package com.example.ejm1.Object

import com.example.ejm1.Api.ConsumirApiCategoria
import com.example.ejm1.Api.ConsumirApiOferta
import com.example.ejm1.Api.ConsumirApiProduct
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://192.168.100.7:8090/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val consumirApi = retrofit.create(ConsumirApiProduct::class.java)
    val consumirApiOferta = retrofit.create(ConsumirApiOferta::class.java)
    val consumirApiCategoria = retrofit.create(ConsumirApiCategoria::class.java)
}