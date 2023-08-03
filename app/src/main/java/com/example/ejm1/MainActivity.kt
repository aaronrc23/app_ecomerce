package com.example.ejm1

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController

import com.example.ejm1.Fragments.ComprasFragment
import com.example.ejm1.Fragments.HomeFragment
import com.example.ejm1.Fragments.PerfilFragment
import com.example.ejm1.databinding.ActivityMainBinding



import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar NavController utilizando el fragmento contenedor (NavHostFragment)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_pb) as NavHostFragment
        navController = navHostFragment.findNavController()



        drawerLayout = findViewById(R.id.drawer_layout)


        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            findViewById(R.id.toolbar),
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        /*sidebar*/
        drawerLayout.addDrawerListener(toggle)

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item clicks here
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    // Navegar al fragmento HomeFragment
                    navController.navigate(R.id.homeFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.categoriaFragment -> {
                    // Navegar al fragmento ComprasFragment
                    navController.navigate(R.id.categoriaFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.comprasFragment -> {
                    // Navegar al fragmento ComprasFragment
                    navController.navigate(R.id.comprasFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.perfilFragment -> {
                    // Navegar al fragmento PerfilFragment
                    navController.navigate(R.id.perfilFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_logout -> {
                    // Handle logout item click
                    Toast.makeText(applicationContext, "Logout clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_contactus -> {
                    // Handle contact us item click
                    Toast.makeText(applicationContext, "Contact us clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_about -> {
                    // Handle about item click
                    Toast.makeText(applicationContext, "About clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }
        /**/

        // Establecer un Listener para el BottomNavigationView
        binding.btnMenu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    // Navegar al fragmento HomeFragment
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.categoriaFragment -> {
                    // Navegar al fragmento ComprasFragment
                    navController.navigate(R.id.categoriaFragment)
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
                else -> false
            }
        }


    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }




    }

