package com.example.marsrealestate.overview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.marsrealestate.R
import com.example.marsrealestate.databinding.FragmentOverviewBinding



class OverviewFragment : Fragment() {

    private lateinit var binding : FragmentOverviewBinding
    private lateinit var viewModel: OverviewViewModel
    private lateinit var adapter: MarsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.title = "Mars RealEstate"
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_overview, container, false)
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)

        binding.overviewViewModel = viewModel
        binding.setLifecycleOwner(this)

        adapter = MarsAdapter(ClickListener { marsProperty ->
            Toast.makeText(context,"${marsProperty.id}",Toast.LENGTH_SHORT).show()
            viewModel.onSelect(marsProperty)

        })
        binding.recyclerView.adapter = adapter

        viewModel.property.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it.toMutableList())
            }
        })

        viewModel.selectedProperty.observe(viewLifecycleOwner, Observer {
            if(it !=null){
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
                viewModel.onNavigateComplete()
            }
        })

        return binding.root
    }


}