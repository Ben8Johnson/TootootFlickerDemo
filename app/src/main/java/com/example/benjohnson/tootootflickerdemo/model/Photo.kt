package com.example.benjohnson.tootootflickerdemo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * @class Photo is the model for handling what flikr returns in an image
 */
class Photo {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("owner")
    @Expose
    var owner: String? = null
    @SerializedName("secret")
    @Expose
    var secret: String? = null
    @SerializedName("server")
    @Expose
    var server: String? = null
    @SerializedName("farm")
    @Expose
    var farm: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("ispublic")
    @Expose
    var ispublic: Int? = null
    @SerializedName("isfriend")
    @Expose
    var isfriend: Int? = null
    @SerializedName("isfamily")
    @Expose
    var isfamily: Int? = null
    @SerializedName("count_faves")
    @Expose
    var countFaves: String? = null
    @SerializedName("count_comments")
    @Expose
    var count_comments: String? = null
}
