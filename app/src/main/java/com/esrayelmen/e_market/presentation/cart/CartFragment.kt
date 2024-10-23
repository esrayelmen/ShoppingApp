package com.esrayelmen.e_market.presentation.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esrayelmen.e_market.R
import com.esrayelmen.e_market.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val cartAdapter =  CartAdapter()
    private val viewModel by viewModels<CartViewModel>()

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedProduct = cartAdapter.cartList[layoutPosition]
            viewModel.deleteCartItems(selectedProduct)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cartList.layoutManager = LinearLayoutManager(context)
        binding.cartList.adapter = cartAdapter
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.cartList)

        cartAdapter.setOnClickListener { cartEntity, isIncrease ->
            viewModel.updateProductQuantity(cartEntity, isIncrease)
        }

        viewModel.getCartItems()
        observe()

    }

    fun observe() {
        viewModel._cartList.observe(viewLifecycleOwner) { cartList ->
            cartAdapter.cartList = cartList

            var totalPrice = 0.0
            cartList.forEach { cartEntity ->
                val price = cartEntity.price.toString().toDouble() * cartEntity.quantity
                totalPrice += price
            }

            if(totalPrice == 0.0) {
                binding.totalPriceText.visibility = View.GONE
            }

            binding.totalPrice = totalPrice.toString()

        }
    }




}