package com.example.mynestedrv

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import com.example.mynestedrv.adapter.MainAdapter
import com.example.mynestedrv.adapter.MoviePagerAdapter
import com.example.mynestedrv.databinding.FragmentMainBinding
import com.example.mynestedrv.model.DataItem
import com.example.mynestedrv.model.DataItemType
import com.example.mynestedrv.model.RecyclerItem
import com.example.mynestedrv.network.ApiClient
import com.example.mynestedrv.repositorty.MainRepository
import com.example.mynestedrv.utils.Status
import com.example.mynestedrv.viewModel.MainViewModel
import com.example.mynestedrv.viewModel.ViewModelFactory


class MainFragment : Fragment() {

    lateinit var mList: ArrayList<DataItem>
    lateinit var binding: FragmentMainBinding
    lateinit var adapter: MainAdapter
    lateinit var viewModel: MainViewModel
    lateinit var pagerAdapter: MoviePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        binding = FragmentMainBinding.bind(view)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(MainRepository(ApiClient.apiService))
        )[MainViewModel::class.java]


        viewModel.getAllMovies().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    adapter = MainAdapter(it.data!!)
                    binding.mainRecyclerView.adapter = adapter
                    pagerAdapter = MoviePagerAdapter(requireActivity(),it.data)
                    binding.viewPager.adapter = pagerAdapter
                }
                else -> {}
            }

        }

        setupCarousel()

        binding.mainRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        return view
    }
    private fun setupCarousel(){

        binding.viewPager.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
            page.alpha = 0.25f + (1 - kotlin.math.abs(position))
        }
        binding.viewPager.setPageTransformer(pageTransformer)

        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.viewPager.addItemDecoration(itemDecoration)
    }




    class HorizontalMarginItemDecoration(context: Context, @DimenRes horizontalMarginInDp: Int) :
        RecyclerView.ItemDecoration() {
        private val horizontalMarginInPx: Int =
            context.resources.getDimension(horizontalMarginInDp).toInt()

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            outRect.right = horizontalMarginInPx
            outRect.left = horizontalMarginInPx
        }
    }
}