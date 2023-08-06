package com.example.ejm1.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejm1.Adapter.CategoriaAdapter
import com.example.ejm1.Adapter.ProductoAdapter
import com.example.ejm1.Clases.Categoria
import com.example.ejm1.Clases.Product
import com.example.ejm1.Object.RetrofitClient
import com.example.ejm1.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriaFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var miAdapter: CategoriaAdapter
    private val categoriaList = mutableListOf<Categoria>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_categoria, container, false)
        recyclerView = view.findViewById(R.id.rcvcardcat)
        val layoutManager = GridLayoutManager(activity, 2) // Cambia el número '2' al número de columnas que desees
        recyclerView.layoutManager = layoutManager

        miAdapter = CategoriaAdapter(categoriaList) // Corregir aquí, usar CategoriaAdapter en lugar de ProductoAdapter
        recyclerView.adapter = miAdapter
        return view





    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Configurar el onItemClickListener para el CategoriaAdapter
        miAdapter.setOnItemClickListener { categoria ->
            // Obtener el idCategoria de la categoría seleccionada
            val categoriaId = categoria.idCategoria

            // Llamar al método para obtener los productos por categoría
            obtenerProductosPorCategoria(categoriaId)
        }

        obtenerProductos()

    }

    private fun obtenerProductos() {
        val retrofitTraer = RetrofitClient.consumirApiCategoria.getTraerCategoria()

        retrofitTraer.enqueue(object : Callback<List<Categoria>> {
            override fun onResponse(call: Call<List<Categoria>>, response: Response<List<Categoria>>) {
                if (response.isSuccessful) {
                    val categorias = response.body()
                    if (categorias != null) {
                        miAdapter.setData(categorias)
                    } else {
                        Toast.makeText(activity, "No se encontraron Categorias.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(activity, "Error al obtener los categoria.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Categoria>>, t: Throwable) {
                Toast.makeText(activity, "Error al consultar API Rest", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun obtenerProductosPorCategoria(categoriaId: Long) {
        val retrofitTraer = RetrofitClient.consumirApiProduct.getProductosPorCategoria(categoriaId)

        retrofitTraer.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    val productos = response.body()
                    if (productos != null) {
                        // Aquí tienes la lista de productos por categoría, puedes mostrarlos en el RecyclerView.
                        // Primero, asegúrate de tener una lista para almacenar los productos en el Fragment.
                        val productList = mutableListOf<Product>()
                        productList.addAll(productos)

                        // Configura el RecyclerView con un LinearLayoutManager
                        val layoutManager = GridLayoutManager(context, 2) // Cambia el número '2' al número de columnas que desees
                        recyclerView.layoutManager = layoutManager

                        // Crea el adaptador y asígnale la lista de productos
                        val miAdapter = ProductoAdapter(productList)
                        recyclerView.adapter = miAdapter
                    } else {
                        Toast.makeText(context, "No se encontraron productos.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Error al obtener los productos.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(context, "Error al consultar API Rest", Toast.LENGTH_SHORT).show()
            }
        })
    }



}