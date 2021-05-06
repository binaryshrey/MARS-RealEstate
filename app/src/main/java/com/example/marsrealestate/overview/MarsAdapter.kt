package com.example.marsrealestate.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marsrealestate.databinding.GridItemListViewBinding
import com.example.marsrealestate.network.MarsProperty

class MarsAdapter(val clickListener : ClickListener) : ListAdapter<MarsProperty, MarsAdapter.ViewHolder>(DiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MarsAdapter.ViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(clickListener,marsProperty)
    }

    class ViewHolder private constructor(val binding : GridItemListViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: ClickListener, marsProperty: MarsProperty){
            binding.property = marsProperty
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup) : ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GridItemListViewBinding.inflate(layoutInflater,parent, false)
                return ViewHolder(binding)
            }
        }
    }

}
class DiffUtilCallback : DiffUtil.ItemCallback<MarsProperty>(){
    override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
        return oldItem == newItem
    }


}

class ClickListener(val clickListener: (marsProperty: MarsProperty) -> Unit){
    fun onClick(marsProperty: MarsProperty) = clickListener(marsProperty)
}

