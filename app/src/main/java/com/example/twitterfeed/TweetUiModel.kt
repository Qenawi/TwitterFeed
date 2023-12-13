package com.example.twitterfeed

import com.example.twitterfeed.database.TweetEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


data class TweetUiModel(
    val id: Long,
    val name: String,
    val time: String,
    val screenName: String,
    val tweet: String,
    val mediaRes: Int,
    val retweetCount: Int,
    val favoriteCount: Int,
    val formattedCreatedAt: String // Formatted date for UI display
)


fun TweetEntity.toUiModel(): TweetUiModel {
    return TweetUiModel(
        id = this.id,
        name = this.name,
        time = this.time,
        screenName = this.screenName,
        tweet = this.tweet,
        mediaRes = this.mediaRes,
        retweetCount = this.retweetCount,
        favoriteCount = this.favoriteCount,
        formattedCreatedAt = formatCreatedAt(this.createdAt)
    )
}

private fun formatCreatedAt(createdAt: Long): String {
    // You can use your preferred date formatting logic here
    // For simplicity, converting milliseconds to a readable format in this example
    val date = Date(createdAt)
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return dateFormat.format(date)
}