package com.example.benjohnson.tootootflickerdemo.model

import com.google.gson.annotations.SerializedName

data class Profile(val country: String = "",
                   val hometown: String = "",
                   val occupation: String = "",
                   val city: String = "",
                   @SerializedName("join_date")
                   val joinDate: String = "",
                   val facebook: String = "",
                   @SerializedName("last_name")
                   val lastName: String = "",
                   val tumblr: String = "",
                   val pinterest: String = "",
                   val instagram: String = "",
                   @SerializedName("profile_description")
                   val profileDescription: String = "",
                   val twitter: String = "",
                   val nsid: String = "",
                   @SerializedName("showcase_set")
                   val showcaseSet: String = "",
                   @SerializedName("showcase_set_title")
                   val showcaseSet_title: String = "",
                   val id: String = "",
                   @SerializedName("first_name")
                   val firstName: String = "",
                   val email: String = "")