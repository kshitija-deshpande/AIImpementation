package com.kshitija.aiimplementation.models

import com.google.gson.annotations.SerializedName

class Post {

    @SerializedName("userId")
    var userId = 0

    @SerializedName("id")
    var id = 0

    @SerializedName("title")
    var title: String? = null

    @SerializedName("body")
    var body: String? = null
}
