package com.example.ejm1.Api

import com.example.ejm1.Clases.Product
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ConsumirApiProduct {

    @GET("producto/listar")
    fun getTraer(): Call<List<Product>>

    @POST("producto/registrar")
    fun registrarCategoria(@Body categoria: Product): Call<String>


}