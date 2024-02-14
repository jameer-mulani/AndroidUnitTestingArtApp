package com.jameermulani.hellounittestingandroid.repository

import androidx.lifecycle.LiveData
import com.jameermulani.hellounittestingandroid.room.Art
import com.jameermulani.hellounittestingandroid.room.ArtDao
import javax.inject.Inject

class ArtLocalRepositoryImpl @Inject constructor(private val artDao: ArtDao) : ArtLocalRepository {
    override suspend fun addArt(art: Art) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

    override fun observeArts(): LiveData<List<Art>> {
        return artDao.getArts()
    }
}