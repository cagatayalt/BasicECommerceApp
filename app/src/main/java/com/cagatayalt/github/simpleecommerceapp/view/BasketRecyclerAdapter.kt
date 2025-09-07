package com.cagatayalt.github.simpleecommerceapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cagatayalt.github.simpleecommerceapp.R
import com.cagatayalt.github.simpleecommerceapp.model.Product

import com.cagatayalt.github.simpleecommerceapp.databinding.BasketRvRowBinding

class BasketRecyclerAdapter(val basketList : List<Product>) : RecyclerView.Adapter<BasketRecyclerAdapter.BasketViewHolder>(){

    class BasketViewHolder(private val binding: BasketRvRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.basketProductNameText.text = product.name
            binding.basketProductPriceText.text = "Price: ${product.price}"
            binding.basketCountText.text = "Count: ${product.count}"
            Glide
                .with(binding.root.context)
                .load(product.url)
                .into(binding.basketImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding = BasketRvRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(basketList[position])
    }

    override fun getItemCount(): Int {
        return basketList.size
    }


}