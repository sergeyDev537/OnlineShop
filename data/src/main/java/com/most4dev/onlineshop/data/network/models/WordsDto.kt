package com.most4dev.onlineshop.data.network.models

import com.google.gson.annotations.SerializedName

data class WordsDto(
    @SerializedName("words")
    val words: List<String>
)