package com.example.mynestedrv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mynestedrv.databinding.FragmentViewPagerMovieBinding
import com.example.mynestedrv.model.movie.Search

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewPagerMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewPagerMovieFragment : Fragment() {
    lateinit var binding: FragmentViewPagerMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager_movie, container, false)
        binding = FragmentViewPagerMovieBinding.bind(view)
        val movie = arguments?.getSerializable("movie") as Search

        Glide.with(view).load(movie.Poster).into(binding.iv)


        return view
    }
}