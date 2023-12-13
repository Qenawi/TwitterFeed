package com.example.twitterfeed.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TweetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "time")
    val time: String,

    @ColumnInfo(name = "screenName")
    val screenName: String,

    @ColumnInfo(name = "tweet")
    val tweet: String,

    @ColumnInfo(name = "mediaRes")
    val mediaRes: Int = 0,

    @ColumnInfo(name = "retweetCount")
    val retweetCount: Int,

    @ColumnInfo(name = "favoriteCount")
    val favoriteCount: Int,

    @ColumnInfo(name = "createdAt")
    val createdAt: Long = System.currentTimeMillis()
)
