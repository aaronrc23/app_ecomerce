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
import com.example.ejm1.Clases.Oferta
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


        // Establece la lista de imágenes en el control de deslizamiento de imágenes
        imageSlider.setImageList(imageList)

        // Llamar al método para obtener las ofertas y productos de la API
        obtenerOfertas(imageSlider)
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


    private fun obtenerOfertas(imageSlider: ImageSlider) {
        val retrofitTraer = RetrofitClient.consumirApiOferta.Obtenerofertas()

        retrofitTraer.enqueue(object : Callback<List<Oferta>> {
            override fun onResponse(call: Call<List<Oferta>>, response: Response<List<Oferta>>) {
                if (response.isSuccessful) {
                    val ofertas = response.body()
                    if (ofertas != null) {
                        // Convertir cada objeto Oferta en un objeto SlideModel y agregarlo a la lista imageList
                        for (oferta in ofertas) {
                            val slideModel = SlideModel(oferta.imagen /*, oferta.titulo*/)
                            imageList.add(slideModel)
                        }

                        // Establecer la lista de SlideModel en el control de deslizamiento de imágenes
                        imageSlider.setImageList(imageList)
                    } else {
                        Toast.makeText(activity, "No se encontraron ofertas.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(activity, "Error al obtener las ofertas.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Oferta>>, t: Throwable) {
                Toast.makeText(activity, "Error al consultar API Rest", Toast.LENGTH_SHORT).show()
            }
        })
    }




}