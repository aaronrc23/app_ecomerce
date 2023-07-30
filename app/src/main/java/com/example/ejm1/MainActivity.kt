package com.example.ejm1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController

import com.example.ejm1.Fragments.ComprasFragment
import com.example.ejm1.Fragments.HomeFragment
import com.example.ejm1.Fragments.PerfilFragment
import com.example.ejm1.databinding.ActivityMainBinding



import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar NavController utilizando el fragmento contenedor (NavHostFragment)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_pb) as NavHostFragment
        navController = navHostFragment.findNavController(  )

        // Establecer un Listener para el BottomNavigationView
        binding.btnMenu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    // Navegar al fragmento HomeFragment
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.comprasFragment -> {
                    // Navegar al fragmento ComprasFragment
                    navController.navigate(R.id.comprasFragment)
                    true
                }
                R.id.perfilFragment -> {
                    // Navegar al fragmento PerfilFragment
                    navController.navigate(R.id.perfilFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}