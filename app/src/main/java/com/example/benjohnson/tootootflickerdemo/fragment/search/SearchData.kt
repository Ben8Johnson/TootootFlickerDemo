package com.example.benjohnson.tootootflickerdemo.fragment.search

import android.os.Handler
import android.os.Looper
import com.example.benjohnson.tootootflickerdemo.IDataReadyCallBack
import com.example.benjohnson.tootootflickerdemo.model.ImageSearchResponse
import com.example.benjohnson.tootootflickerdemo.utils.NetworkingUtils
import com.example.benjohnson.tootootflickerdemo.utils.NetworkingUtils.Companion.buildResponseAsJson
import com.example.benjohnson.tootootflickerdemo.utils.Utils
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

/**
 * @class SearchData
 *
 * Data class for the search page
 */
class SearchData {

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
    fun searchForImages(dataReadyCallback: IDataReadyCallBack.IPhotoDataReadyCallback, searchQuery: String) {
        //Create request
        val request = Request.Builder().url(buildUrl(searchQuery)).build()
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
    private fun buildUrl(searchQuery: String): String {
        val urlBuilder = HttpUrl.parse(NetworkingUtils.BASE_URL)!!.newBuilder()
        urlBuilder.addQueryParameter(NetworkingUtils.METHOD_PARAM, NetworkingUtils.Companion.FLIKR_METHOD.SEARCH.method)
        urlBuilder.addQueryParameter(NetworkingUtils.API_KEY_PARAM, Utils.API_KEY)
        urlBuilder.addQueryParameter(SEARCH_EXTRAS, NetworkingUtils.GET_EXTRAS_PARAM)
        urlBuilder.addQueryParameter(SEARCH_QUEREY_PARAM, searchQuery)
        urlBuilder.addQueryParameter(RESULT_SIZE_PARAM, Utils.DEFAULT_RESULT_SIZE.toString())
        urlBuilder.addQueryParameter(RESPONSE_TYPE_PARAM, NetworkingUtils.DEFAULT_RESPONSE_FORMAT)
        return urlBuilder.build().toString()
    }


}