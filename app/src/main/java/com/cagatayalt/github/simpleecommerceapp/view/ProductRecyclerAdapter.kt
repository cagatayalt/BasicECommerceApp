package com.cagatayalt.github.simpleecommerceapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cagatayalt.github.simpleecommerceapp.R
import com.cagatayalt.github.simpleecommerceapp.model.Product
import com.cagatayalt.github.simpleecommerceapp.databinding.ProductsRvRowBinding

class ProductRecyclerAdapter(
    val productList : List<Product>,
    private val listener : Listener
    ): RecyclerView.Adapter<ProductRecyclerAdapter.ProductHolder>() {


    class ProductHolder(private val binding: ProductsRvRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, listener: Listener) {
            binding.productName.text = product.name
            binding.productPrice.text = product.price
            Glide.with(binding.root.context).load(product.url).into(binding.productImage)
            binding.addBasketButton.setOnClickListener {
                Toast.makeText(it.context, "${product.name} added to Basket", Toast.LENGTH_LONG).show()
                listener.onItemClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ProductsRvRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(productList[position], listener)
    }

    override fun getItemCount(): Int {
        return productList.size
    }


    interface Listener {
        fun onItemClick(product: Product)
    }


}