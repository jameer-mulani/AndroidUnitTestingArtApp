package com.jameermulani.hellounittestingandroid.repository

import com.jameermulani.hellounittestingandroid.model.ImageResponse
import com.jameermulani.hellounittestingandroid.util.Resource

class FakeImageSearchRepository : ImageSearchRepository {
    override suspend fun searchImage(searchQuery: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(hits = listOf(), total = 0, totalHits = 0))
    }
}