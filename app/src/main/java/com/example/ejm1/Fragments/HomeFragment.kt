package com.example.ejm1.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.ejm1.R
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejm1.Adapter.ProductoAdapter
import com.example.ejm1.Clases.Product
import com.example.ejm1.Object.RetrofitClient


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var miAdapter: ProductoAdapter
    private val productList = mutableListOf<Product>()

    // Crea la lista de imágenes
    private val imageList = ArrayList<SlideModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Obtener la referencia al RecyclerView
        recyclerView = view.findViewById(R.id.rcvcardprod)

        // Configurar el RecyclerView con un LinearLayoutManager
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        // Crear e inicializar el adaptador
        miAdapter = ProductoAdapter(productList)
        recyclerView.adapter = miAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener referencia al ImageSlider
        val imageSlider = view.findViewById<ImageSlider>(R.id.image_slider)

        // Agrega las imágenes y los títulos a la lista de imágenes
        imageList.add(SlideModel("https://bit.ly/2YoJ77H"))
        imageList.add(SlideModel("https://bit.ly/2BteuF2"))
        imageList.add(SlideModel("https://bit.ly/3fLJf72"))

        // Establece la lista de imágenes en el control de deslizamiento de imágenes
        imageSlider.setImageList(imageList)

        // Llamar al método para obtener los productos de la API
        obtenerProductos()
    }

    private fun obtenerProductos() {
        val retrofitTraer = RetrofitClient.consumirApi.getTraer()

        retrofitTraer.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    val productos = response.body()
                    if (productos != null) {
                        miAdapter.setData(productos) // Llenar el adaptador con los productos obtenidos
                    } else {
                        Toast.makeText(activity, "No se encontraron productos.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(activity, "Error al obtener los productos.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(activity, "Error al consultar API Rest", Toast.LENGTH_SHORT).show()
            }
        })
    }





}