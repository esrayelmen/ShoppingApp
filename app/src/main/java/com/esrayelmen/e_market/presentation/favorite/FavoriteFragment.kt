package com.esrayelmen.e_market.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.esrayelmen.e_market.R
import com.esrayelmen.e_market.data.model.ProductResponse
import com.esrayelmen.e_market.databinding.FragmentFavoriteBinding
import com.esrayelmen.e_market.presentation.home.HomeFragmentDirections
import com.esrayelmen.e_market.presentation.home.ProductAdapter
import com.esrayelmen.e_market.presentation.home.ProductClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment: Fragment(R.layout.fragment_favorite), ProductClickListener{

    private lateinit var binding : FragmentFavoriteBinding
    private val productAdapter = ProductAdapter(this)
    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favoriteList.layoutManager = GridLayoutManager(context, 2)
        binding.favoriteList.adapter = productAdapter

        viewModel.getFavorites()
        observe()
        //observeIsFavorite()

        /*productAdapter.setOnFavoriteClickListener { product ->
            viewModel.setFavorites(product)
        }

         */

    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.favorites.collect { favorites ->
                productAdapter.productList = favorites
            }
        }

    }

    private fun observeIsFavorite() {
        lifecycleScope.launch {
            viewModel.isFavorite.collect { isFavorite ->
                productAdapter.productList.forEach { product ->
                    product.isFavorite = isFavorite
                }
            }
        }
    }

    override fun onProductClicked(productResponse: ProductResponse) {
        val id = productResponse.id
        val name = productResponse.name.toString()
        val action = id?.let { FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(id,name) }
        action?.let {
            findNavController(requireView()).navigate(action)
        }
    }

    override fun onFavoriteClicked(productResponse: ProductResponse) {
        viewModel.setFavorites(productResponse)
        //productResponse.isFavorite = !productResponse.isFavorite

        //holder.view.favoriteIcon.isSelected = productList[position].isFavorite
    }

    override fun onAddToCartClicked(productResponse: ProductResponse) {
        viewModel.addToCart(productResponse)
    }


}