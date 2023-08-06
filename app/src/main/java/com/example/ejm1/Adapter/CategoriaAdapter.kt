package com.example.ejm1.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ejm1.Clases.Categoria
import com.example.ejm1.Clases.Product
import com.example.ejm1.R

import androidx.navigation.NavDirections
import androidx.navigation.findNavController

class CategoriaAdapter (private var dataList: List<Categoria>):RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>(){


    // 2. Establecer el clic en la card de categoría
    private var onItemClickListener: ((Categoria) -> Unit)? = null

    fun setOnItemClickListener(listener: (Categoria) -> Unit) {
        onItemClickListener = listener
    }

    class CategoriaViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val categoriaImage: ImageView = itemView.findViewById(R.id.imgcategoria)
        val categoriaNameTextView: TextView = itemView.findViewById(R.id.txtcattitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        // Inflar la vista de la cardview desde el layout xml
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_categoria, parent, false)
        return CategoriaAdapter.CategoriaViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val category = dataList[position]

        Glide.with(holder.itemView.context)
            .load(category.imagen)
            .into(holder.categoriaImage)

        holder.categoriaNameTextView.text =category.nombre
        /*---*/
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("categoryId" to category.idCategoria)
            holder.itemView.findNavController().navigate(R.id.catalogoFragment, bundle)
        }

        // 2. Configurar el clic en la card de categoría
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(category)
        }
    }

    fun setData(categoria: List<Categoria>) {
        dataList = categoria
        notifyDataSetChanged()
    }


}