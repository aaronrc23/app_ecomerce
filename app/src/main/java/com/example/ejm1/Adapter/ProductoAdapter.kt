package com.example.ejm1.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView


import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ejm1.Clases.Product
import com.example.ejm1.R

class ProductoAdapter(private var dataList: List<Product>): RecyclerView.Adapter<ProductoAdapter.ProductViewHolder>() {


    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.imgcard)
        val productNameTextView: TextView = itemView.findViewById(R.id.txtTitle)
        val productPriceTextView: TextView = itemView.findViewById(R.id.txtprecio)
        val addButton: ImageButton = itemView.findViewById(R.id.btnadd) // Cambiar Button por ImageButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        // Inflar la vista de la cardview desde el layout xml
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_producto, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        // Aquí configura las vistas con los datos del elemento en la posición 'position'
        val product = dataList[position]

        // Establecer la imagen, título y precio en las vistas correspondientes de la cardview
        Glide.with(holder.itemView.context)
            .load(product.imagen)
            .into(holder.productImage)
        holder.productNameTextView.text = product.descripcion
        holder.productPriceTextView.text = product.precio.toString()
        holder.addButton.setOnClickListener {
            // Tu lógica aquí cuando se haga clic en el botón
            // Por ejemplo, agregar el producto al carrito, etc.
        }

        // Puedes agregar aquí algún OnClickListener para el botón si deseas realizar alguna acción al hacer clic en él
        // Por ejemplo: holder.addButton.setOnClickListener { // tu lógica aquí }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(productos: List<Product>) {
        dataList = productos
        notifyDataSetChanged()
    }

}