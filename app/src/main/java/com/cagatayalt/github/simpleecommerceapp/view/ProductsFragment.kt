package com.cagatayalt.github.simpleecommerceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cagatayalt.github.simpleecommerceapp.R
import com.cagatayalt.github.simpleecommerceapp.model.Product
import com.cagatayalt.github.simpleecommerceapp.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_product.*


class ProductsFragment : Fragment() , ProductRecyclerAdapter.Listener{



    private val productViewModel : ProductViewModel by activityViewModels()
    private var productRecyclerAdapter : ProductRecyclerAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)





    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        productsRV.layoutManager = GridLayoutManager(activity?.baseContext,2)




        productViewModel.downloadData()

        productViewModel.productList.observe(viewLifecycleOwner, Observer {
            productRecyclerAdapter = ProductRecyclerAdapter(it,this)

            productsRV.adapter = productRecyclerAdapter


        })

    }

    override fun onItemClick(product: Product) {
        productViewModel.addToBasket(product)

    }


}