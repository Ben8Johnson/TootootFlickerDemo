package com.example.benjohnson.tootootflickerdemo.fragment.homepage

import android.os.Handler
import android.os.Looper
import com.example.benjohnson.tootootflickerdemo.IDataReadyCallBack
import com.example.benjohnson.tootootflickerdemo.model.ImageSearchResponse
import com.example.benjohnson.tootootflickerdemo.utils.NetworkingUtils
import com.example.benjohnson.tootootflickerdemo.utils.NetworkingUtils.Companion.API_KEY_PARAM
import com.example.benjohnson.tootootflickerdemo.utils.NetworkingUtils.Companion.BASE_URL
import com.example.benjohnson.tootootflickerdemo.utils.NetworkingUtils.Companion.DEFAULT_RESPONSE_FORMAT
import com.example.benjohnson.tootootflickerdemo.utils.NetworkingUtils.Companion.GET_EXTRAS_PARAM
import com.example.benjohnson.tootootflickerdemo.utils.NetworkingUtils.Companion.METHOD_PARAM
import com.example.benjohnson.tootootflickerdemo.utils.Utils.Companion.API_KEY
import com.example.benjohnson.tootootflickerdemo.utils.Utils.Companion.DEFAULT_RESULT_SIZE
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

/**
 * @class HomePageData is the data class for the homepage which makes a call to the flickr api for
 * popular photos
 */
class HomePageData {

    //Image search parameters
    private val SEARCH_QUEREY_PARAM = "text"
    private val RESULT_SIZE_PARAM = "per_page"
    private val RESPONSE_TYPE_PARAM = "format"
    private val SEARCH_EXTRAS = "extras"

    /**
     * Default constructor
     */
    private val client = OkHttpClient()

    /**
     * Makes a request to obtain data about nearby places of interest
     */
    fun searchForImages(dataReadyCallback: IDataReadyCallBack.IPhotoDataReadyCallback) {
        //Create request
        val request = Request.Builder().url(buildUrl()).build()
        //Schedule call to be executed in async task
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val gson = GsonBuilder().create()
                val result = buildResponseAsJson(response)
                val mainHandler = Handler(Looper.getMainLooper())
                val myRunnable = Runnable {
                    dataReadyCallback.onDataReady(gson.fromJson(result, ImageSearchResponse::class.java).photo!!.photo!!)
                }
                mainHandler.post(myRunnable)
            }
        })


    }

    /**
     * Builds the url with endpoint and required parameters
     */
    private fun buildUrl(): String {
        val urlBuilder = HttpUrl.parse(BASE_URL)!!.newBuilder()
        urlBuilder.addQueryParameter(METHOD_PARAM, NetworkingUtils.Companion.FLIKR_METHOD.RECENT_PHOTOS.method)
        urlBuilder.addQueryParameter(API_KEY_PARAM, API_KEY)
        urlBuilder.addQueryParameter(SEARCH_EXTRAS, GET_EXTRAS_PARAM)
        urlBuilder.addQueryParameter(RESULT_SIZE_PARAM, DEFAULT_RESULT_SIZE.toString())
        urlBuilder.addQueryParameter(RESPONSE_TYPE_PARAM, DEFAULT_RESPONSE_FORMAT)
        return urlBuilder.build().toString()
    }

    /**
     * The flikr api returns non json complient characters at the begining of the
     * response so we have to trim them off before the result can be used
     */
    private fun buildResponseAsJson(response: Response): String {
        var result = response.body()!!.string()
        result = result.substring(14, result.length - 1)
        return result
    }

}