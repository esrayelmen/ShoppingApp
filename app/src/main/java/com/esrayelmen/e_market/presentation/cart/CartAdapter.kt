package com.esrayelmen.e_market.presentation.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.esrayelmen.e_market.R
import com.esrayelmen.e_market.data.model.CartEntity
import com.esrayelmen.e_market.data.model.ProductResponse
import com.esrayelmen.e_market.databinding.CartItemBinding
import com.esrayelmen.e_market.databinding.ProductItemBinding

class CartAdapter() : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(val view: CartItemBinding) :RecyclerView.ViewHolder(view.root)

    private var onQuantityChangeListener : ((CartEntity, Boolean) -> Unit)? = null

    private val diffUtil = object : DiffUtil.ItemCallback<CartEntity>() {
        override fun areItemsTheSame(oldItem: CartEntity, newItem: CartEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CartEntity, newItem: CartEntity): Boolean {
            return oldItem == newItem
        }
    }

    private val listDiffer = AsyncListDiffer(this,diffUtil)
    var cartList: List<CartEntity>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CartItemBinding>(inflater, R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    fun setOnClickListener(listener : ((CartEntity, Boolean) -> Unit)) {
        onQuantityChangeListener = listener
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.view.product = cartList[position]

        holder.view.minusBtn.setOnClickListener {
            onQuantityChangeListener?.let {
                it(cartList[position], false)
            }
        }

        holder.view.plusBtn.setOnClickListener {
            onQuantityChangeListener?.let {
                it(cartList[position], true)
            }
        }

    }


}