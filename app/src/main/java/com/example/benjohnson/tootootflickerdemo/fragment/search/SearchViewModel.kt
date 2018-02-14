package com.example.benjohnson.tootootflickerdemo.fragment.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.example.benjohnson.tootootflickerdemo.IDataReadyCallBack
import com.example.benjohnson.tootootflickerdemo.model.Photo

/**
 * @class SearchViewModel
 */
class SearchViewModel : ViewModel(), IDataReadyCallBack.IPhotoDataReadyCallback {

    //observable edit text for search
    var searchText = ObservableField<String>()
    //data class
    private val data = SearchData()
    //image list observable
    var imageList = MutableLiveData<List<Photo>>()


    /**
     * When a user clicks the search button we first check the search and then
     * we send it to the data class to make a request
     */
    fun searchBtnClicked() {
        if (fieldValid()) {
            data.searchForImages(this, searchText.get())
        }
    }

    /**
     * Checks if the search input is valid before we make the request
     */
    private fun fieldValid(): Boolean {
        if (searchText.get().length > 1)
            return true

        return false
    }

    /**
     * Request was successful and there are places to show the user
     * @param data is the results from the request
     */
    override fun onDataReady(data: List<Photo>) {
        if (data.isEmpty())
            return
        imageList.value = data
    }
}