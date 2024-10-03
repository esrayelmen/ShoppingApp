package com.esrayelmen.e_market.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.esrayelmen.e_market.R
import com.esrayelmen.e_market.databinding.FragmentHomeBinding
import com.esrayelmen.e_market.presentation.cart.CartAdapter
import com.esrayelmen.e_market.presentation.main.MainViewModel
import com.esrayelmen.e_market.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val productAdapter = ProductAdapter()
    private val cartAdapter = CartAdapter()
    val homeViewModel by viewModels<HomeViewModel>()
    //private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productList.layoutManager = GridLayoutManager(context, 2)
        binding.productList.adapter = productAdapter

        observeViewModel()
        observeSearchQuery()
        if (homeViewModel.products.value.isEmpty()) {
            homeViewModel.getProducts()
        }

        productAdapter.setOnItemClickListener { product ->
            homeViewModel.addToCart(product)
            //println(product.id)
        }
    }

    fun observeViewModel() {
        lifecycleScope.launch {
            homeViewModel.products.collect { products ->
                productAdapter.productList = products
            }
        }

        /*lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                fragmentHomeBinding.progressBar.visibility =
                    if (isLoading) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launch {
            viewModel.errorMessage.collect { errorMessage ->
                errorMessage?.let {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                } ?: run {

                }
            }
        }

         */
    }

    fun observeSearchQuery() {
        lifecycleScope.launch {
            homeViewModel.searchResult.collect {
                productAdapter.productList = it
            }
        }
    }





}