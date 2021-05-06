package com.example.marsrealestate.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarsProperty(
    val id : String,
    val price : Double,
    val type : String,
    @Json(name = "img_src") val imgSrcUrl : String
) : Parcelable