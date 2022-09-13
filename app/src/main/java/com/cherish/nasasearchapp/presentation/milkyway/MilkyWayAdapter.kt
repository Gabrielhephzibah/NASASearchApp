package com.cherish.nasasearchapp.presentation.milkyway

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.cherish.nasasearchapp.databinding.MilkyWayLayoutBinding

class MilkyWayAdapter(private val onItemClick: (MilkyWayItem?) -> Unit):
    ListAdapter<MilkyWayItem, MilkyWayViewHolder>(MilkyWayComparator){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MilkyWayViewHolder {
        val binding =
            MilkyWayLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MilkyWayViewHolder(binding) {
            onItemClick(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: MilkyWayViewHolder, position: Int) {
        getItem(position)?.let { postListItem -> holder.bind(postListItem) }
    }


    object MilkyWayComparator : DiffUtil.ItemCallback<MilkyWayItem>() {
        override fun areItemsTheSame(
            oldItem: MilkyWayItem,
            newItem: MilkyWayItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MilkyWayItem,
            newItem: MilkyWayItem
        ): Boolean {
            return oldItem == newItem
        }
    }


}