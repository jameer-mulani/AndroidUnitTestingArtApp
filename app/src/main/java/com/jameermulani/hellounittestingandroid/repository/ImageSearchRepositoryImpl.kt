package com.jameermulani.hellounittestingandroid.repository

import com.jameermulani.hellounittestingandroid.api.Api
import com.jameermulani.hellounittestingandroid.model.ImageResponse
import com.jameermulani.hellounittestingandroid.util.Resource
import javax.inject.Inject

class ImageSearchRepositoryImpl @Inject constructor(
    private val api: Api
) : ImageSearchRepository {

    override suspend fun searchImage(searchQuery: String): Resource<ImageResponse> {
        val searchImageResponse = api.searchImage(searchQuery)
        return try {
            if (searchImageResponse.isSuccessful) {
                val imageResponse = searchImageResponse.body()
                imageResponse?.let {
                    Resource.success(it)
                } ?: Resource.error("No body received, try again")
            } else {
                Resource.error("Something went wrong, please try again")
            }
        } catch (e: Exception) {
            Resource.error(e.message.toString())
        }
    }
}