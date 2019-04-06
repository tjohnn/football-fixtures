package com.tjohnn.footballfixtures.data.source.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.tjohnn.footballfixtures.data.dto.League

@Dao
abstract class LeagueDao {

    @Insert
    abstract fun insertLeague(league: League)

    @Insert
    abstract fun insertAllLeagues(leagues: List<League>)

    @Query("DELETE FROM leagues")
    abstract fun deleteAllLeagues()

    @Query("SELECT * FROM leagues")
    abstract fun getLeagues(): LiveData<List<League>>

    @Transaction
    open fun refreshLeaguesTable(leagues: List<League>){
        deleteAllLeagues()
        insertAllLeagues(leagues)
    }

}