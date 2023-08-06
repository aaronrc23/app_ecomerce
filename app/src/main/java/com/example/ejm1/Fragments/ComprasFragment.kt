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

class ComprasFragment : Fragment() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         return inflater.inflate(R.layout.fragment_compras, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Crear e inicializar el adaptador


    }


}