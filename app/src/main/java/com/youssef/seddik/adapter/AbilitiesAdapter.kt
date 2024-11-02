package com.youssef.seddik.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.youssef.seddik.data.Ability
import com.youssef.seddik.databinding.ItemAbilityBinding
import javax.inject.Inject

class AbilitiesAdapter @Inject constructor() :
    RecyclerView.Adapter<AbilitiesAdapter.AbilityViewHolder>() {
    private val items = mutableListOf<Ability>()
    private var isLoadingAdded = false

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        val ability = items[position]
        holder.bind(ability)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        return AbilityViewHolder(
            ItemAbilityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addMoreData(data: List<Ability>) {
        val startPosition = items.size
        items.addAll(data)
        val endPosition = items.size
        notifyItemRangeInserted(startPosition, endPosition)
    }

    inner class AbilityViewHolder(private val binding: ItemAbilityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ability) {
            binding.apply {
                name.text = item.name
            }
        }
    }
}