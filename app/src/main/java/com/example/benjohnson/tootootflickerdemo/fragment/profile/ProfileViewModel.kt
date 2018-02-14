package com.example.benjohnson.tootootflickerdemo.fragment.profile

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import com.example.benjohnson.tootootflickerdemo.IDataReadyCallBack
import com.example.benjohnson.tootootflickerdemo.model.Profile

/**
 * Created by BenJohnson on 12/02/2018.
 */
class ProfileViewModel : ViewModel(), IDataReadyCallBack.IProfileDataReadyCallback {


    //observable edit text for search
    var username = ObservableField<String>()
    var realName = ObservableField<String>()
    var searchText = ObservableField<String>()
    val data = ProfileData()

    /**
     * When a user clicks the search button we first check the search and then
     * we send it to the data class to make a request
     */
    fun searchBtnClicked() {
        if (fieldValid()) {
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

    override fun onDataReady(data: Profile) {
        Log.d("dsbdsjkbfkjdbds", data.lastName)

    }
}