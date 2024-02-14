package com.jameermulani.hellounittestingandroid.repository

import com.jameermulani.hellounittestingandroid.model.ImageResponse
import com.jameermulani.hellounittestingandroid.util.Resource

interface ImageSearchRepository {
    suspend fun searchImage(searchQuery : String) : Resource<ImageResponse>
}