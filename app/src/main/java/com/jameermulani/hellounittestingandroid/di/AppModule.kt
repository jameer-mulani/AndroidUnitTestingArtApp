package com.jameermulani.hellounittestingandroid.di

import android.content.Context
import androidx.room.Room
import com.jameermulani.hellounittestingandroid.api.Api
import com.jameermulani.hellounittestingandroid.room.ArtDatabase
import com.jameermulani.hellounittestingandroid.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideArtDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ArtDatabase::class.java, "ArtDatabase").build()

    @Provides
    @Singleton
    fun provideArtDao(artDatabase: ArtDatabase) = artDatabase.artDao()

    @Provides
    @Singleton
    fun provideArtApi(): Api {
        return Retrofit.Builder().baseUrl(Util.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)
    }

}