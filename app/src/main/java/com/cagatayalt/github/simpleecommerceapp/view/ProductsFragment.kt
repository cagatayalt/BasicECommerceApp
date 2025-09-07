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
import com.cagatayalt.github.simpleecommerceapp.databinding.FragmentProductBinding


class ProductsFragment : Fragment() , ProductRecyclerAdapter.Listener{
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private val productViewModel : ProductViewModel by activityViewModels()
    private var productRecyclerAdapter : ProductRecyclerAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root





    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.productsRV.layoutManager = GridLayoutManager(activity?.baseContext,2)




        productViewModel.downloadData()

        productViewModel.productList.observe(viewLifecycleOwner, Observer {
            productRecyclerAdapter = ProductRecyclerAdapter(it,this)

            binding.productsRV.adapter = productRecyclerAdapter


        })

    }

    override fun onItemClick(product: Product) {
        productViewModel.addToBasket(product)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}