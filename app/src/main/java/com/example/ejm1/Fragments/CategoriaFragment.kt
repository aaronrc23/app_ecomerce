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
import com.example.ejm1.Clases.Categoria
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

        miAdapter = CategoriaAdapter(categoriaList)
        recyclerView.adapter = miAdapter
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



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

}