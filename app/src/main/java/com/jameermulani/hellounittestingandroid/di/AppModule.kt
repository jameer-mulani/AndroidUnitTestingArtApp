package com.jameermulani.hellounittestingandroid.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jameermulani.hellounittestingandroid.R
import com.jameermulani.hellounittestingandroid.api.Api
import com.jameermulani.hellounittestingandroid.repository.ArtLocalRepository
import com.jameermulani.hellounittestingandroid.repository.ArtLocalRepositoryImpl
import com.jameermulani.hellounittestingandroid.repository.ImageSearchRepository
import com.jameermulani.hellounittestingandroid.repository.ImageSearchRepositoryImpl
import com.jameermulani.hellounittestingandroid.repository.ImageSearchRepositoryImpl_Factory
import com.jameermulani.hellounittestingandroid.room.ArtDao
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

    @Provides
    @Singleton
    fun provideArtLocalRepository(artDao: ArtDao): ArtLocalRepository {
        return ArtLocalRepositoryImpl(artDao)
    }

    @Provides
    @Singleton
    fun provideImageSearchRepository(api: Api): ImageSearchRepository {
        return ImageSearchRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGlide(@ApplicationContext context: Context) = Glide
        .with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )

}