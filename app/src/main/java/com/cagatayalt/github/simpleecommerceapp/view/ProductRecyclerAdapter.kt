package com.cagatayalt.github.simpleecommerceapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cagatayalt.github.simpleecommerceapp.R
import com.cagatayalt.github.simpleecommerceapp.model.Product
import kotlinx.android.synthetic.main.products_rv_row.view.*

class ProductRecyclerAdapter(
    val productList : List<Product>,
    private val listener : Listener
    ): RecyclerView.Adapter<ProductRecyclerAdapter.ProductHolder>() {


    class ProductHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.products_rv_row,parent,false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.itemView.productName.text = productList[position].name
        holder.itemView.productPrice.text = productList[position].price
        Glide.with(holder.itemView.context).load(productList[position].url).into(holder.itemView.productImage)
        holder.itemView.addBasketButton.setOnClickListener{
            Toast.makeText(it.context,"${productList[position].name} added to Basket ",Toast.LENGTH_LONG).show()
            listener.onItemClick(productList[position])

        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }


    interface Listener {
        fun onItemClick(product: Product)
    }


}