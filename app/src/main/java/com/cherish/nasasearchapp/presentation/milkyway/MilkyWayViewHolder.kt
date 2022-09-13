package com.cherish.nasasearchapp.presentation.milkyway

import androidx.recyclerview.widget.RecyclerView
import com.cherish.nasasearchapp.common.extension.executeWithAction
import com.cherish.nasasearchapp.databinding.MilkyWayLayoutBinding

class MilkyWayViewHolder(
   private val binding: MilkyWayLayoutBinding,
    onItemClick: (Int) -> Unit
 ): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            onItemClick(bindingAdapterPosition)
        }
    }
    fun bind(milkyWayItem: MilkyWayItem) {
        binding.executeWithAction {
            this.milkyWayItem = milkyWayItem
        }
    }
}