package com.tjohnn.footballfixtures.data.source.remote


import com.tjohnn.footballfixtures.data.dto.ArrayResponse
import com.tjohnn.footballfixtures.data.dto.League
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("competitions")
    fun loadLeagues(): Single<ArrayResponse<League>>


}
