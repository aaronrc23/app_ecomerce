package com.example.ejm1.Api


import com.example.ejm1.Clases.Oferta
import com.example.ejm1.Clases.Product
import retrofit2.Call

import retrofit2.http.GET

interface ConsumirApiOferta {

    @GET("ofertas/listar")
    fun Obtenerofertas(): Call<List<Oferta>>



}