package com.tjohnn.footballfixtures.data.dto

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "leagues")
data class League(
        @PrimaryKey(autoGenerate = false)
        var id: Long = 0,
        val name: String
)