package com.example.twitterfeed.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess


interface TweetApiService {
    suspend fun getLatestTweets(): List<TweetDataModel>
}

class TweetApiException(message: String) : Exception(message)


class KtorTweetApiService(private val httpClient: HttpClient = HttpClient()) : TweetApiService {
    override suspend fun getLatestTweets(): List<TweetDataModel> {
        val response = httpClient.get("url")
        if (response.status.isSuccess()) {
            return response.body()
        } else {
            throw TweetApiException("Error fetching latest tweets")
        }
    }

}