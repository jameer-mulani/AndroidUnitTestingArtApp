package com.jameermulani.hellounittestingandroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jameermulani.hellounittestingandroid.room.Art

class FakeArtLocalRepositoryImpl  : ArtLocalRepository{

    private val arts  = mutableListOf<Art>()
    private val artLiveData = MutableLiveData<List<Art>>(arts)

    override suspend fun addArt(art: Art) {
        arts.add(art)
        refresh()
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
        refresh()
    }

    override fun observeArts(): LiveData<List<Art>> {
        return artLiveData
    }

    private fun refresh(){
        artLiveData.postValue(arts)
    }

}