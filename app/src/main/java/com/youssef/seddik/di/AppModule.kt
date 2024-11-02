package com.youssef.seddik.di

import android.content.Context
import androidx.room.Room
import com.youssef.seddik.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.youssef.seddik.data.dao.Dao
import com.youssef.seddik.data.database.Database
import com.youssef.seddik.data.repository.GetAbilitiesRepo
import com.youssef.seddik.domain.GetAbilitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): Database {
        return Room.databaseBuilder(
            appContext,
            Database::class.java,
            "room_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesDao(database: Database): Dao =
        database.abilityDao()

    @Provides
    @Singleton
    fun providesDogsRepo(apiService: ApiService, dao: Dao) =
        GetAbilitiesRepo(apiService, dao)


    @Provides
    @Singleton
    fun providesDogsUseCase(getAbilitiesRepo: GetAbilitiesRepo) =
        GetAbilitiesUseCase(getAbilitiesRepo)


    @Provides
    @Singleton
    fun moshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(moshi: Moshi): ApiService =
        Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)

}