package com.cagatayalt.github.simpleecommerceapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.layout.Layout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cagatayalt.github.simpleecommerceapp.R
import com.cagatayalt.github.simpleecommerceapp.model.Product

import kotlinx.android.synthetic.main.basket_rv_row.view.*

class BasketRecyclerAdapter(val basketList : List<Product>) : RecyclerView.Adapter<BasketRecyclerAdapter.BasketViewHolder>(){

    class BasketViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.basket_rv_row,parent,false
        )

        return BasketViewHolder(view)

    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {

        holder.itemView.basketProductNameText.text = basketList[position].name
        holder.itemView.basketProductPriceText.text = "Price: ${basketList[position].price}"
        holder.itemView.basketCountText.text = "Count: ${basketList[position].count}"
        Glide
            .with(holder.itemView.context)
            .load(basketList[position].url)
            .into(holder.itemView.basketImageView)

    }

    override fun getItemCount(): Int {
        return basketList.size
    }


}