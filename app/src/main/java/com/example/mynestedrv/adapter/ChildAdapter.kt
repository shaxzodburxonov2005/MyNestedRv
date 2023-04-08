package com.example.mynestedrv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynestedrv.databinding.ItemBannerBinding
import com.example.mynestedrv.databinding.ItemMovieBinding
import com.example.mynestedrv.model.DataItem
import com.example.mynestedrv.model.DataItemType
import com.example.mynestedrv.model.RecyclerItem

class ChildAdapter( var viewType: Int, val recyclerItemList:List<RecyclerItem>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class BestSellerViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindBestSellerView(recyclerItem: RecyclerItem) {
            binding.imgTop.setImageResource(recyclerItem.image)
            binding.tvFilm.text = recyclerItem.offer

        }
    }

    inner class ClothingViewHolder(val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindClothingView(recyclerItem: RecyclerItem) {
            binding.bannerImg.setImageResource(recyclerItem.image)
            binding.tvBanner.text = recyclerItem.offer
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            DataItemType.BEST_SELLER -> {
                val binding =
                    ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return BestSellerViewHolder(binding)
            }
            else -> {
                val binding =
                    ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ClothingViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return recyclerItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BestSellerViewHolder -> {
                holder.bindBestSellerView(recyclerItemList[position])
            }
            is ClothingViewHolder -> {
                holder.bindClothingView(recyclerItemList[position])
            }
        }
    }


}
