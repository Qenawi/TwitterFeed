package com.example.twitterfeed.api

import com.example.twitterfeed.database.TweetEntity
import kotlinx.serialization.Serializable

@Serializable
data class TweetDataModel(
    val name: String,
    val time: String,
    val screenName: String,
    val tweet: String,
    val mediaRes: Int = 0,
    val retweetCount: Int,
    val favoriteCount: Int
)

fun TweetDataModel.mapToDataEntityModel(): TweetEntity
{
    return TweetEntity(
        name = this.name,
        time = this.time,
        screenName = this.screenName,
        tweet = this.tweet,
        mediaRes = this.mediaRes,
        retweetCount = this.retweetCount,
        favoriteCount = this.favoriteCount
    )
}