package com.younes.paybackcodingchallenge.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.younes.paybackcodingchallenge.api.RetrofitAPI
import com.younes.paybackcodingchallenge.repo.MainRepository
import com.younes.paybackcodingchallenge.repo.MainRepositoryInterface
import com.younes.paybackcodingchallenge.roomdb.ImageDao
import com.younes.paybackcodingchallenge.roomdb.ImageDatabase
import com.younes.paybackcodingchallenge.util.Util.BASE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.younes.paybackcodingchallenge.R
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

    @Singleton
    @Provides
    fun injectRoomDatabase(
            @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ImageDatabase::class.java,"ArtBookDB").build()

    @Singleton
    @Provides
    fun injectDao(
        database: ImageDatabase
    ) = database.artDao()


    @Singleton
    @Provides
    fun injectRetrofitAPI() : RetrofitAPI {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build().create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectRepo(dao : ImageDao, api: RetrofitAPI) = MainRepository(dao,api) as MainRepositoryInterface

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide
            .with(context).setDefaultRequestOptions(
                    RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_foreground)
            )

}