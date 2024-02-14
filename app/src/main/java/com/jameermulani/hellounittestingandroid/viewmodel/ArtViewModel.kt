package com.jameermulani.hellounittestingandroid.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jameermulani.hellounittestingandroid.api.Api
import com.jameermulani.hellounittestingandroid.model.ImageResponse
import com.jameermulani.hellounittestingandroid.repository.ArtLocalRepository
import com.jameermulani.hellounittestingandroid.repository.ImageSearchRepository
import com.jameermulani.hellounittestingandroid.room.Art
import com.jameermulani.hellounittestingandroid.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(
    private val artLocalRepository: ArtLocalRepository,
    private val imageRemoteRepository: ImageSearchRepository
) : ViewModel() {

    val artsList = artLocalRepository.observeArts()

    private var _selectedImageUrl = MutableLiveData<String>()

    val selectedImageUrl: LiveData<String>
        get() = _selectedImageUrl


    private var _images = MutableLiveData<Resource<ImageResponse>>()
    val images: LiveData<Resource<ImageResponse>>
        get() = _images

    fun resetImageObserver(){
        _images = MutableLiveData<Resource<ImageResponse>>()
    }

    fun searchImage(searchImage : String){
        if (searchImage.isEmpty())
            return

        _images.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            val response = imageRemoteRepository.searchImage(searchImage)
            _images.postValue(response)
        }

    }

    private var _insertArt = MutableLiveData<Resource<Art>>()
    val insertArtMessage: LiveData<Resource<Art>>
        get() = _insertArt

    fun resetInsertArt() {
        _insertArt = MutableLiveData<Resource<Art>>()
    }

    fun resetSelectedImageUrl(){
        _selectedImageUrl = MutableLiveData<String>()
    }

    fun deleteArt(art: Art) {
        viewModelScope.launch(Dispatchers.IO) {
            artLocalRepository.deleteArt(art)
        }
    }

    fun makeArt(name: String, artistName: String, year: String) {
        if (name.isEmpty() || artistName.isEmpty() || year.isEmpty()) {
            _insertArt.postValue(Resource.error("Enter all the details"))
            return
        }

        val yearInt = try {
            year.toInt()
        } catch (e: Exception) {
            _insertArt.postValue(Resource.error("Invalid Year, please check"))
            return
        }

        val art = Art(
            name = name,
            artistName = artistName,
            year = yearInt,
            imageUrl = selectedImageUrl.value ?: ""
        )

        insertArt(art)
//        imageSearchViewModel.setSelectedImage("")
        _insertArt.postValue(Resource.success(art))
    }

    private fun insertArt(art: Art) = viewModelScope.launch(Dispatchers.IO) {
        artLocalRepository.addArt(art)
    }

    fun setSelectedImage(url: String) {
        _selectedImageUrl.postValue(url)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ArtAppViewModel", "onCleared: ArtViewModel")
    }
}