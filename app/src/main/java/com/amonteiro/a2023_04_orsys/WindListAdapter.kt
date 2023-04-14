package com.amonteiro.a2023_04_orsys

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.amonteiro.a2023_04_orsys.databinding.RowWindBinding

class WindListAdapter : ListAdapter<WindBean, WindListAdapter.ViewHolder>(Comparator()) {

    class ViewHolder(val binding : RowWindBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

    class Comparator : DiffUtil.ItemCallback<WindBean>() {
        override fun areItemsTheSame(oldItem: WindBean, newItem: WindBean)
         = oldItem === newItem

        override fun areContentsTheSame(oldItem: WindBean, newItem: WindBean)
         = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    = ViewHolder(RowWindBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.tvtemp.text = "-${item.speed}"
    }

}