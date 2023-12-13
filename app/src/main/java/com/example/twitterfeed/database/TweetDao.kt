package com.example.twitterfeed.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Dao
interface TweetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTweets(tweets: List<TweetEntity>)

    @Query("SELECT * FROM TweetEntity ORDER BY createdAt DESC")
    suspend fun getLastTweets(): List<TweetEntity>
}

@Database(entities = [TweetEntity::class], version = 1)
abstract class TweetDataBase : RoomDatabase() {
    abstract fun tweetDao(): TweetDao
}

object DatabaseBuilder {
    private var INSTANCE: TweetDataBase? = null
    fun getInstance(context: Context): TweetDataBase {
        if (INSTANCE == null) {
            synchronized(TweetDataBase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context,
            TweetDataBase::class.java,
            "database"
        ).build()
}
