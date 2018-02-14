package com.example.benjohnson.tootootflickerdemo.fragment.homepage

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.benjohnson.tootootflickerdemo.R
import com.example.benjohnson.tootootflickerdemo.adapter.ImageResultAdapter
import com.example.benjohnson.tootootflickerdemo.databinding.FragmentHomepageBinding
import com.example.benjohnson.tootootflickerdemo.fragment.BaseFragment
import com.example.benjohnson.tootootflickerdemo.model.Photo

/**
 * @class HomePageFragment will display a feed of popular images on flikr
 */
class HomePageFragment : BaseFragment(), ImageResultAdapter.OnItemClickListener {

    private lateinit var viewModel: HomePageViewModel
    private lateinit var binding: FragmentHomepageBinding


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_homepage, container, false)
        viewModel = ViewModelProviders.of(this).get(HomePageViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    /**
     * Once the views been created we want to create the recyclerview and then search
     * for popular photos to fill it with
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.searchForPopularPhotos()
    }

    /**
     * Creates recycler view to display images in
     */
    private fun setupRecyclerView() {
        //Setup recycler view with adapter
        binding.rvImages.layoutManager = LinearLayoutManager(context)
        var adapter = ImageResultAdapter(arrayListOf(), this)
        binding.rvImages.adapter = adapter
        //Create an observer on the viewmodel to update the adapter once the data is changed
        viewModel.imageList.observe(this, Observer<List<Photo>> {
            it?.let {
                adapter.replaceData(it)
            }
        })
    }

    /**
     * On click event for when a user clicks on a photo
     */
    override fun onItemClick(item: Photo) {

    }
}