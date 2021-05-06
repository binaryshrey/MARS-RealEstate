package com.example.marsrealestate.overview

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.marsrealestate.R
import com.example.marsrealestate.databinding.FragmentOverviewBinding
import com.example.marsrealestate.network.MarsApiFilter


class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding
    private lateinit var viewModel: OverviewViewModel
    private lateinit var adapter: MarsAdapter
    private var filter = MarsApiFilter.SHOW_ALL

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.title = "Mars RealEstate"
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)

        binding.overviewViewModel = viewModel
        binding.setLifecycleOwner(this)

        adapter = MarsAdapter(ClickListener { marsProperty ->
            Toast.makeText(context, "${marsProperty.id}", Toast.LENGTH_SHORT).show()
            viewModel.onSelect(marsProperty)

        })
        binding.recyclerView.adapter = adapter

        viewModel.property.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it.toMutableList())
            }
        })

        viewModel.selectedProperty.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController()
                    .navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
                viewModel.onNavigateComplete()
            }
        })

        binding.onSwipeRefresh.setOnRefreshListener {
//            Toast.makeText(context,"refreshed",Toast.LENGTH_SHORT).show()
            viewModel.getMarsRealEstateProperties(filter)
            binding.onSwipeRefresh.isRefreshing = false
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.buy -> {
                viewModel.UpdateFilter(MarsApiFilter.SHOW_BUY)
                filter = MarsApiFilter.SHOW_BUY
            }
            R.id.rent -> {
                viewModel.UpdateFilter(MarsApiFilter.SHOW_RENT)
                filter = MarsApiFilter.SHOW_RENT
            }
            else -> {
                viewModel.UpdateFilter(MarsApiFilter.SHOW_ALL)
                filter = MarsApiFilter.SHOW_ALL
            }
        }



        return true
    }
}