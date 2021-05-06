package com.example.marsrealestate.network

import androidx.lifecycle.LiveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit

import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL= "https://mars.udacity.com/"

enum class MarsApiFilter(val value : String){
    SHOW_RENT("rent"),
    SHOW_BUY("buy"),
    SHOW_ALL("all")
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MarsAPIService{
    @GET("realestate")
    suspend fun getProperties(@Query("filter") type : String) :
            List<MarsProperty>
}


object MarsApi{
    val retrofitService : MarsAPIService by lazy {
        retrofit.create(MarsAPIService::class.java)
    }
}