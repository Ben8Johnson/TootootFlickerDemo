package com.example.benjohnson.tootootflickerdemo.fragment.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import com.example.benjohnson.tootootflickerdemo.R
import com.example.benjohnson.tootootflickerdemo.adapter.ImageResultAdapter
import com.example.benjohnson.tootootflickerdemo.databinding.FragmentSearchBinding
import com.example.benjohnson.tootootflickerdemo.fragment.BaseFragment
import com.example.benjohnson.tootootflickerdemo.model.Photo


/**
 * @class SearchFragment
 *
 * In this class a user can use a search term to find images
 */
class SearchFragment : BaseFragment(), OnEditorActionListener, ImageResultAdapter.OnItemClickListener {


    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    /**
     * When the view is created we need to listen for the user input and create a recycler view
     * which will listen for search results once the user has made a query
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //SetOnClickListener
        binding.etSearch.setOnEditorActionListener(this)
        setupRecyclerView()
    }

    /**
     * Creates generic recycler view to display images in
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
     * When the user has clicked the enter keyboard button
     */
    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        viewModel.searchBtnClicked()
        return true
    }

    /**
     * On click event for when a user clicks on a photo
     */
    override fun onItemClick(item: Photo) {

    }
}