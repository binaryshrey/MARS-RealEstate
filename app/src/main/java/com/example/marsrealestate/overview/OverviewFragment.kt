package com.example.marsrealestate.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.marsrealestate.R
import com.example.marsrealestate.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private lateinit var binding : FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_overview, container, false)
        return binding.root
    }


}