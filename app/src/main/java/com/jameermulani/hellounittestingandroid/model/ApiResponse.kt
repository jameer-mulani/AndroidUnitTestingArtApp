package com.jameermulani.hellounittestingandroid.model

data class ImageResponse(
    val hits : List<ImageResult>,
    val total : Int,
    val totalHits : Int
)

data class ImageResult(
    val previewURL : String,
    val largeImageURL : String,
)