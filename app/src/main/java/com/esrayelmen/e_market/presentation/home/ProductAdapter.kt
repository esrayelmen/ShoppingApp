package com.esrayelmen.e_market.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.esrayelmen.e_market.R
import com.esrayelmen.e_market.databinding.ProductItemBinding
import com.esrayelmen.e_market.data.model.ProductResponse

class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(), ProductClickListener {

    class ProductViewHolder(val view : ProductItemBinding) : RecyclerView.ViewHolder(view.root)

    private var onItemClickListener : ((ProductResponse) -> Unit)? = null

    private val diffUtil = object : DiffUtil.ItemCallback<ProductResponse>() {
        override fun areItemsTheSame(oldItem: ProductResponse, newItem: ProductResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductResponse, newItem: ProductResponse): Boolean {
            return oldItem == newItem
        }
    }

    private val listDiffer = AsyncListDiffer(this,diffUtil)
    var productList: List<ProductResponse>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ProductItemBinding>(inflater, R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setOnItemClickListener(listener : ((ProductResponse) -> Unit)) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.view.product = productList[position]
        holder.view.listener = this
        //println(holder.view.product?.imageUrl)

        holder.view.addToCartBtn.setOnClickListener {
            onItemClickListener?.let {
                it(productList[position])
            }
        }
    }

    override fun onProductClicked(v: View) {
        val binding = DataBindingUtil.getBinding<ProductItemBinding>(v)
        val id = binding?.product?.id
        val name = binding?.product?.name.toString()
        val action = id?.let { HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id,name) }
        action?.let {
            findNavController(v).navigate(action)
        }
    }

}