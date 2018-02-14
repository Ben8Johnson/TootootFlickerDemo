package com.example.benjohnson.tootootflickerdemo.utils

import okhttp3.Response

/**
 * @class NetworkingUtils
 *
 * Some reusable networking functions and constants
 */
class NetworkingUtils {

    companion object {

        const val METHOD_PARAM = "method"
        const val API_KEY_PARAM = "api_key"
        const val DEFAULT_RESPONSE_FORMAT: String = "json"
        const val BASE_URL: String = "https://api.flickr.com/services/rest/"
        const val IMAGE_URL = "https://farm%s.staticflickr.com/%s/%s_%s.jpg"
        const val GET_EXTRAS_PARAM = "count_faves,count_comments"

        /**
         * Generates a url for a flikr image
         */
        fun generateImageUrl(farmId: String, serverId: String, imageId: String, seceret: String): String {
            return String.format(IMAGE_URL, farmId, serverId, imageId, seceret)
        }


        /**
         * Enum containing all the flikr api methods which the app is using
         */
        enum class FLIKR_METHOD(val method: String) {
            SEARCH("flickr.photos.search"),
            RECENT_PHOTOS("flickr.interestingness.getList"),
            FIND_USER("flickr.people.findByUsername"),
            GET_PROFILE("flickr.profile.getProfile"),
        }

        /**
         * The flikr api returns non json complient characters at the begining of the
         * response so we have to trim them off before the result can be used
         */
        fun buildResponseAsJson(response: Response): String {
            var result = response.body()!!.string()
            result = result.substring(14, result.length - 1)
            return result
        }
    }
}