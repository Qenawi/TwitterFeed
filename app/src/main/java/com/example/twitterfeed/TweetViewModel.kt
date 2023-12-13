package com.example.twitterfeed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.twitterfeed.database.DatabaseBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TweetViewModel(
    private val application: Application,
    val repository: TweetRepository = TweetRepository(
        tweetDao = DatabaseBuilder.getInstance(application).tweetDao()
    )
) : AndroidViewModel(application) {

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getLatestTweets()
            _dataList.postValue(data)

        }

    }

    private val _dataList = MutableLiveData<List<TweetUiModel>>()
    val dataList: LiveData<List<TweetUiModel>> = _dataList
}