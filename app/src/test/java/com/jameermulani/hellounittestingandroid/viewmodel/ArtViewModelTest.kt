package com.jameermulani.hellounittestingandroid.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.*
import com.jameermulani.hellounittestingandroid.MainCoroutineRule
import com.jameermulani.hellounittestingandroid.getOrAwaitValueTest
import com.jameermulani.hellounittestingandroid.repository.FakeArtLocalRepositoryImpl
import com.jameermulani.hellounittestingandroid.repository.FakeImageSearchRepository
import com.jameermulani.hellounittestingandroid.util.STATUS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ArtViewModelTest {

    @get:Rule
    var instanceTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var artViewModel: ArtViewModel

    @Before
    fun setup(){
        artViewModel = ArtViewModel(FakeArtLocalRepositoryImpl(), FakeImageSearchRepository())
    }

    @Test
    fun `when empty year provided then returns error`(){
        artViewModel.makeArt("MonaLisa", "Davinci", "")
        val insertValue = artViewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(insertValue.status).isEqualTo(STATUS.FAILED)
    }

    @Test
    fun `when empty art name provided then returns error`(){
        artViewModel.makeArt("", "DaVinci", "1500")
        val insertMessage = artViewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(insertMessage.status).isEqualTo(STATUS.FAILED)
    }

    @Test
    fun `when empty artist name is provided then returns error`(){
        artViewModel.makeArt(name = "Monda", "", "1200")
        val insertedMessage = artViewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(insertedMessage.status).isEqualTo(STATUS.FAILED)
    }

    @Test
    fun `when invalid year value given then returns error`(){
        artViewModel.makeArt(name = "MonaLista", "DavInci", year = "#2Bac")
        val insertedMessage = artViewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(insertedMessage.status).isEqualTo(STATUS.FAILED)
    }

    @Test
    fun `when all correct values provided to makeArt then returns success`(){
        artViewModel.makeArt("Von", "Ghuch", "1234")
        val insertedMessage = artViewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(insertedMessage.status).isEqualTo(STATUS.SUCCESS)
    }

}