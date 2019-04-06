package com.tjohnn.footballfixtures.data.dto

import com.google.gson.annotations.SerializedName

data class ArrayResponse<T>(
        val message: String,
        @SerializedName(value = "data", alternate = ["competitions"])
        val data: List<T>
)