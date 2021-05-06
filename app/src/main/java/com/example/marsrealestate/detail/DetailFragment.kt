package com.example.marsrealestate.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.FloatRange
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.marsrealestate.R
import com.example.marsrealestate.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val application = requireNotNull(activity).application
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.setLifecycleOwner(this)
        val args = DetailFragmentArgs.fromBundle(arguments!!).selectedProperty

        Log.i("DetailFragment","id : ${args.imgSrcUrl}")

        viewModelFactory = DetailViewModelFactory(args,application)
        viewModel = ViewModelProvider(this,viewModelFactory).get(DetailViewModel::class.java)
        binding.detailViewModel = viewModel

        return binding.root
    }


}