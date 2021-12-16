package com.example.headsupprep.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.headsupprep.Model.CelebrityItem
import com.example.headsupprep.databinding.ItemRowBinding

class RecyclerViewAdapter(private var celebrityList: List<CelebrityItem>):
    RecyclerView.Adapter<RecyclerViewAdapter.HolderView>() {
    class HolderView(val binding:ItemRowBinding): RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderView {
        return HolderView(ItemRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: HolderView, position: Int) {
       val celebrity = celebrityList[position]
        holder.binding.apply {
            tvName.text = celebrity.name
            tvTaboo1.text = celebrity.taboo1
            tvTaboo2.text = celebrity.taboo2
            tvTaboo3.text = celebrity.taboo3
        }
    }

    override fun getItemCount(): Int {
        return celebrityList.size
    }


}