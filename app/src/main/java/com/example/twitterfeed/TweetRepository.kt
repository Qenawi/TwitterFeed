package com.example.twitterfeed

import com.example.twitterfeed.api.KtorTweetApiService
import com.example.twitterfeed.api.TweetApiService
import com.example.twitterfeed.api.mapToDataEntityModel
import com.example.twitterfeed.database.TweetDao

class TweetRepository(private val apiService: TweetApiService = KtorTweetApiService() , private val tweetDao:TweetDao) {

    suspend fun getLatestTweets(): List<TweetUiModel> {
        // Check for cached data in Room
        val cachedTweets = tweetDao.getLastTweets()
        if (cachedTweets.isNotEmpty())
        {
            return cachedTweets.map {it.toUiModel() }
        }

        // Fetch fresh data from the API
        val apiTweets = apiService.getLatestTweets()
        tweetDao.insertTweets(apiTweets.map { it.mapToDataEntityModel() })
        // todo enhance
        return apiTweets.map { it.mapToDataEntityModel() }.map { it.toUiModel() } }
    }