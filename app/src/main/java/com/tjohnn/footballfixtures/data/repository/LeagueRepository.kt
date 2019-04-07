package com.tjohnn.footballfixtures.data.repository

import com.tjohnn.footballfixtures.data.dto.League
import com.tjohnn.footballfixtures.data.source.local.dao.LeagueDao
import com.tjohnn.footballfixtures.data.source.remote.ApiService
import io.reactivex.Completable
import javax.inject.Inject


class LeagueRepository
@Inject constructor(
        private val apiService: ApiService,
        private val leagueDao: LeagueDao
){
    fun getLeagues() = leagueDao.getLeagues()

    fun loadLeagues() = apiService.loadLeagues()
    fun refreshLeagues(leagues: List<League>) : Completable = Completable.fromAction{leagueDao.refreshLeaguesTable(leagues)}
    fun loadLeagueTable(leagueId: Long) = apiService.loadTables(leagueId)
}