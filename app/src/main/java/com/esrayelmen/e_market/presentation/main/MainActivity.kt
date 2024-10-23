package com.esrayelmen.e_market.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.esrayelmen.e_market.databinding.ActivityMainBinding
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.esrayelmen.e_market.R
import com.esrayelmen.e_market.presentation.detail.DetailsFragmentArgs
import com.esrayelmen.e_market.presentation.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var toolBar: Toolbar
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        toolBar = binding.materialToolbar
        setSupportActionBar(toolBar)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController

        toolBar.setNavigationOnClickListener{
            navController.popBackStack()
        }

        setupBottomNav()

        navController.addOnDestinationChangedListener { _, destination, arguments ->
            when(destination.id) {
                R.id.homeFragment -> {
                    viewModel.menuVisibility(true,true)
                    toolBar.title = "E-Market"
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }

                R.id.detailsFragment -> {
                    viewModel.menuVisibility(false,false)
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    arguments?.let {
                        val name = DetailsFragmentArgs.fromBundle(it).productName
                        toolBar.title = name
                    }
                }

                R.id.cartFragment -> {
                    viewModel.menuVisibility(false,false)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    toolBar.title = "Cart"
                }

                R.id.favoriteFragment -> {
                    viewModel.menuVisibility(true,true)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    toolBar.title = "Favorites"
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem = toolBar.menu.findItem(R.id.search)
        val filterItem = toolBar.menu.findItem(R.id.filter)
        val searchView = searchItem?.actionView as SearchView

        lifecycleScope.launch {
            viewModel.menuVisibility.collect{
                searchItem.isVisible = it.isSearchVisible
                filterItem.isVisible = it.isSearchVisible
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            val fragment by lazy {
                //val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
                navHostFragment.childFragmentManager.fragments[0] as? HomeFragment
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    fragment?.homeViewModel?.search(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    fragment?.homeViewModel?.search(it)
                }
                return false
            }
        })

        return true
    }

    private fun setupBottomNav() {
        //NavigationUI.setupWithNavController(binding.bottomNav,navController)  //binding.bottomNav.setupWithNavController(navController)
        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.cart -> {
                    navController.navigate(R.id.cartFragment)
                    true
                }
                R.id.favorite -> {
                    navController.navigate(R.id.favoriteFragment)
                    true
                }
                else -> false
            }


        }
    }

    /*override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

     */

}