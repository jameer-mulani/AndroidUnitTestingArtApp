package com.jameermulani.hellounittestingandroid.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.jameermulani.hellounittestingandroid.getOrAwaitValueTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
class ArtDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: ArtDao
    private lateinit var artDatabase: ArtDatabase

    @Before
    fun setup() {
        artDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), ArtDatabase::class.java,
        ).allowMainThreadQueries().build()
        dao = artDatabase.artDao()
    }

    @After
    fun tearDown() {
        artDatabase.close()
    }

    @Test
    fun testInsertArt() = runBlocking {
        val exampleArt = Art(
            name = "MonaLisaTest",
            artistName = "DaVinci Test",
            year = 1703,
            id = 1,
            imageUrl = "example.test"
        )

        dao.insertArt(exampleArt)

        val getArts = dao.getArts().getOrAwaitValueTest()

        assertThat(getArts).contains(exampleArt)
    }

    @Test
    fun testDeleteArt() = runBlocking {

        val exampleArt = Art(
            name = "MonaLisaTest",
            artistName = "DaVinci Test",
            year = 1703,
            id = 1,
            imageUrl = "example.test"
        )

        dao.insertArt(exampleArt)

        var getArts = dao.getArts().getOrAwaitValueTest()
        assertThat(getArts).contains(exampleArt)
        dao.deleteArt(exampleArt)
        getArts = dao.getArts().getOrAwaitValueTest()
        assertThat(getArts).doesNotContain(exampleArt)

    }


}