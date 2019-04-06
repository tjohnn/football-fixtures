package com.tjohnn.footballfixtures.di

import android.content.Context
import com.tjohnn.footballfixtures.data.source.local.AppDatabase
import com.tjohnn.footballfixtures.data.source.local.dao.LeagueDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    internal fun appDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun favoriteDao(appDatabase: AppDatabase): LeagueDao {
        return appDatabase.leagueDao
    }

}
