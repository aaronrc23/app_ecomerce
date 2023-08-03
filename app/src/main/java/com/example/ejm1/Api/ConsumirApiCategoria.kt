package com.example.ejm1.Api

import com.example.ejm1.Clases.Categoria
import retrofit2.Call
import retrofit2.http.GET

interface ConsumirApiCategoria {

    @GET("categoria/listar")
    fun getTraerCategoria(): Call<List<Categoria>>
}