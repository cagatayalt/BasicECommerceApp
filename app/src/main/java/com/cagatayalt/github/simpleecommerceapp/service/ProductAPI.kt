package com.cagatayalt.github.simpleecommerceapp.service

import com.cagatayalt.github.simpleecommerceapp.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {

    // BASE URL -> https://raw.githubusercontent.com/

    //

    @GET("atilsamancioglu/BTK23-DataSet/main/products.json")
    suspend fun getData() : Response<List<Product>>





}