package com.example.benjohnson.tootootflickerdemo.fragment.homepage

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.benjohnson.tootootflickerdemo.IDataReadyCallBack
import com.example.benjohnson.tootootflickerdemo.model.Photo

/**
 * @class HomePageViewModel
 */
class HomePageViewModel : ViewModel(), IDataReadyCallBack.IPhotoDataReadyCallback {

    //data class
    private val data = HomePageData()

    var imageList = MutableLiveData<List<Photo>>()

    /**
     * Makes a request to search for popular photos
     */
    fun searchForPopularPhotos() {
        data.searchForImages(this)
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