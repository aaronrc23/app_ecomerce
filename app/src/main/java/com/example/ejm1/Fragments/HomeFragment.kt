package com.example.ejm1.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.ejm1.R

class HomeFragment : Fragment() {

    // Crea la lista de imágenes
    private val imageList = ArrayList<SlideModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
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
    }

}