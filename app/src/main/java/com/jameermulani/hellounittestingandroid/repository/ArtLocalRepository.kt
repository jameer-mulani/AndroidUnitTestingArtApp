package com.jameermulani.hellounittestingandroid.repository

import androidx.lifecycle.LiveData
import com.jameermulani.hellounittestingandroid.model.ImageResponse
import com.jameermulani.hellounittestingandroid.room.Art
import com.jameermulani.hellounittestingandroid.util.Resource

interface ArtLocalRepository {

    suspend fun addArt(art : Art)

    suspend fun deleteArt(art: Art)

    fun observeArts() : LiveData<List<Art>>

}