package com.example.marsrealestate.network

import com.squareup.moshi.Json

data class MarsProperty(
    val id : String,
    val price : Double,
    val type : String,
    @Json(name = "img_src") val imgSrcUrl : String
)