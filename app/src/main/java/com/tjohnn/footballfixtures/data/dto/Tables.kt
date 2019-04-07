package com.tjohnn.footballfixtures.data.dto

import com.google.gson.annotations.SerializedName

data class Tables(
        val standings: List<Standing>,
        val competition: Competition
)

data class Competition(
        val name: String
)
data class Standing(
        val table: List<TableItem>
)
data class TableItem(
        val position: Int,
        @SerializedName("playedGames")
        val played: Int,
        val won: Int,
        val draw: Int,
        val lost: Int,
        val points: Int,
        val team: Team,
        @SerializedName("goalsFor")
        val goalsFor: Int,
        @SerializedName("goalsAgainst")
        val goalsAgainst: Int,
        @SerializedName("goalDifference")
        val goalDifference: Int

)

data class Team(
        val id: Int,
        val name: String
)
