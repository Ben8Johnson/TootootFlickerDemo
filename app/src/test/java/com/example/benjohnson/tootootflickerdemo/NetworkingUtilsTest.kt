package com.example.benjohnson.tootootflickerdemo

import com.example.benjohnson.tootootflickerdemo.utils.NetworkingUtils
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by BenJohnson on 14/02/2018.
 */
class NetworkingUtilsTest {

    @Test
    fun testThatImageUrlReturnsCorrectString() {
        val expected: String = "https://farmabcde.staticflickr.com/fghijk/lmnop_qrstuvwxyz.jpg"
        val actual: String = NetworkingUtils.generateImageUrl("abcde", "fghijk", "lmnop", "qrstuvwxyz")
        assertEquals(expected, actual)
    }

    @Test
    fun testThatCorrectMethodForSearch() {
        val expected: String = "flickr.photos.search"
        val actual: String = NetworkingUtils.Companion.FLIKR_METHOD.SEARCH.method
        assertEquals(expected, actual)
    }

    @Test
    fun testThatCorrectMethodForRecentPhotos() {
        val expected: String = "flickr.interestingness.getList"
        val actual: String = NetworkingUtils.Companion.FLIKR_METHOD.RECENT_PHOTOS.method
        assertEquals(expected, actual)
    }

    @Test
    fun testThatJsonStringIsMadeCorrectly() {

    }

}