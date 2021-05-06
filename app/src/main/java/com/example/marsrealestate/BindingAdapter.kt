package com.example.marsrealestate

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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

@BindingAdapter("progressStatus")
    fun status(progressBar: ProgressBar, status : MarsApiStatus){
        when(status){
            MarsApiStatus.LOADING ->{
                progressBar.visibility = View.VISIBLE
            }
            MarsApiStatus.DONE -> {
                progressBar.visibility = View.INVISIBLE
            }
            MarsApiStatus.ERROR -> {
                progressBar.visibility = View.INVISIBLE
            }
        }
    }

@BindingAdapter("errorStatus")
fun errorStatus(errorTextView: TextView, marsApiStatus: MarsApiStatus) {
    when (marsApiStatus) {
        MarsApiStatus.LOADING -> {
            errorTextView.visibility = View.INVISIBLE
        }
        MarsApiStatus.DONE -> {
            errorTextView.visibility = View.INVISIBLE
        }
        MarsApiStatus.ERROR -> {
            errorTextView.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("recyclerViewStatus")
fun recyclerViewStatus(recyclerView: RecyclerView, marsApiStatus: MarsApiStatus) {
    when (marsApiStatus) {
        MarsApiStatus.LOADING -> {
            recyclerView.visibility = View.INVISIBLE
        }
        MarsApiStatus.DONE -> {
            recyclerView.visibility = View.VISIBLE
        }
        MarsApiStatus.ERROR -> {
            recyclerView.visibility = View.INVISIBLE
        }
    }
}