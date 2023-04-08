package com.example.mynestedrv.adapter

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mynestedrv.ViewPagerMovieFragment
import com.example.mynestedrv.model.movie.Search

class MoviePagerAdapter(fragmentActivity: FragmentActivity, val listSearch: List<Search>) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return listSearch.size
    }

    override fun createFragment(position: Int): Fragment = ViewPagerMovieFragment().apply {
        arguments = Bundle().apply {
            putSerializable("movie", listSearch[position])
        }
    }

}