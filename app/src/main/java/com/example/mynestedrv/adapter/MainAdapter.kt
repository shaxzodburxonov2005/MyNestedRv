package com.example.mynestedrv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynestedrv.R
import com.example.mynestedrv.databinding.ItemBannerBinding
import com.example.mynestedrv.databinding.ItemParentBinding
import com.example.mynestedrv.databinding.ItemTopBinding
import com.example.mynestedrv.model.*
import com.example.mynestedrv.model.movie.Search

class MainAdapter(private val dataItemList: List<Search>) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    inner class RecyclerItemViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(search: Search) {
            Glide.with(itemView).load(search.Poster).into(binding.bannerImg)
            binding.tvBanner.text = search.Title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            ItemBannerBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int = dataItemList.size

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.onBind(dataItemList[position])
    }
}