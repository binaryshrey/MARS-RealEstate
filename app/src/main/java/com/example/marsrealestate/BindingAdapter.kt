package com.example.marsrealestate

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.marsrealestate.overview.MarsApiStatus


@BindingAdapter("imageUrl")
fun bind(image : ImageView, imgUrl : String?){
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(image.context)
            .load(imgUri)
            .into(image)
    }
}

//@BindingAdapter("bind:marsApiStatus")
//    fun status(progressBar: ProgressBar,errorTextView: TextView, status : MarsApiStatus){
//        when(status){
//            MarsApiStatus.LOADING ->{
//                progressBar.visibility = View.VISIBLE
//                errorTextView.visibility = View.INVISIBLE
//            }
//            MarsApiStatus.DONE -> {
//                progressBar.visibility = View.INVISIBLE
//                errorTextView.visibility = View.INVISIBLE
//            }
//            MarsApiStatus.ERROR -> {
//                progressBar.visibility = View.INVISIBLE
//                errorTextView.visibility = View.VISIBLE
//            }
//        }
//    }
