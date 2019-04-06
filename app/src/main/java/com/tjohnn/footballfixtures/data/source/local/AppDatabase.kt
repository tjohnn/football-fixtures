package com.tjohnn.footballfixtures.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.tjohnn.footballfixtures.data.dto.League
import com.tjohnn.footballfixtures.data.source.local.dao.LeagueDao

@Database(entities = [League::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val leagueDao: LeagueDao

    companion object {

        private const val DATABASE_NAME = "football_fixtures"

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = synchronized(this) {
            return INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }


        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigrationFrom(1)
                        .build()

    }

}