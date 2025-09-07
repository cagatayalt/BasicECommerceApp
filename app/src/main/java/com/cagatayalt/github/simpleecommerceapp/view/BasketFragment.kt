package com.cagatayalt.github.simpleecommerceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cagatayalt.github.simpleecommerceapp.R
import com.cagatayalt.github.simpleecommerceapp.viewmodel.ProductViewModel
import com.cagatayalt.github.simpleecommerceapp.databinding.FragmentBasketBinding

class BasketFragment : Fragment() {
    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by activityViewModels()
    private var basketRecyclerAdapter : BasketRecyclerAdapter? = null

    private val swipeCallBack = object  : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            if (basketRecyclerAdapter!=null) {
                val selectedProduct = basketRecyclerAdapter!!.basketList[layoutPosition]
                productViewModel.deleteProductFromBasket(selectedProduct)
                basketRecyclerAdapter!!.notifyDataSetChanged()



            }
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.basketRecyclerView.layoutManager = LinearLayoutManager(
            activity?.baseContext)

        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.basketRecyclerView)

        productViewModel.basket.observe(viewLifecycleOwner, Observer {
            basketRecyclerAdapter = BasketRecyclerAdapter(it)
            binding.basketRecyclerView.adapter = basketRecyclerAdapter


            productViewModel.totalBasket.observe(viewLifecycleOwner, Observer {
                binding.totalBasketText.text = "Total Basket: ${it}"

            })




        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}