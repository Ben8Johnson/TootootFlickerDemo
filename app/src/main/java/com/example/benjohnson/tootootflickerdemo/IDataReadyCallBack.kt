package com.example.benjohnson.tootootflickerdemo

import com.example.benjohnson.tootootflickerdemo.model.Photo
import com.example.benjohnson.tootootflickerdemo.model.Profile


/**
 * @class IDataReadyCallback
 *
 * A set of data ready callbacks
 */
interface IDataReadyCallBack {

    /**
     * Callback for when photo data is available
     */
    interface IPhotoDataReadyCallback {
        fun onDataReady(data: List<Photo>)
    }

    /**
     * Callback for when photo data is available
     */
    interface IProfileDataReadyCallback {
        fun onDataReady(data: Profile)
    }
}