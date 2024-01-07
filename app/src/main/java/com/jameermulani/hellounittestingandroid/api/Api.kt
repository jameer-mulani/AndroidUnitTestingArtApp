package com.jameermulani.hellounittestingandroid.api

import com.jameermulani.hellounittestingandroid.model.ImageResponse
import com.jameermulani.hellounittestingandroid.util.Util
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/api/")
    suspend fun searchImage(
        @Query("q") search: String,
        @Query("key") apiKey: String = Util.API_KEY) : Response<ImageResponse>

}