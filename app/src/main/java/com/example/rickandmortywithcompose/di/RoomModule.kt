package com.example.rickandmortywithcompose.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmortywithcompose.data.datasource.local.AppDatabase
import com.example.rickandmortywithcompose.data.datasource.local.dao.CharacterDao
import com.example.rickandmortywithcompose.data.repository.local.RoomRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "favorite-characters"
        ).build()

    @Provides
    @Singleton
    fun provideCharacterDao(db: AppDatabase): CharacterDao =
        db.characterDao()

    @Provides
    @Singleton
    fun provideRoomRepositoryImpl(characterDao: CharacterDao) =
        RoomRepositoryImpl(characterDao)
}