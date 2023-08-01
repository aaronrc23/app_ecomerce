package com.example.ejm1.Clases

data class Product(
    val categoria: Categoria,
    val descripcion: String,
    val nombre: String,
    val precio: Double,
    val productoId: Int,
    val stock: Int,
    val imagen: String
)