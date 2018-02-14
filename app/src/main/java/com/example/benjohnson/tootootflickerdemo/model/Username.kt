package com.example.benjohnson.tootootflickerdemo.model

import com.google.gson.annotations.SerializedName

data class Username(@SerializedName("_content")
                    val Content: String = "")