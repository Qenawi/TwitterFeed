package com.example.twitterfeed
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner

@Composable
fun TwitterFeed(feedItems: List<TweetUiModel>) {
    LazyColumn {
        items(feedItems) { tweet ->
            TweetItem(tweet = tweet)
            Divider()
        }
    }
}

@Composable
fun TweetItem(tweet: TweetUiModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { /* Handle click on tweet */ }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_twitter_blue),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(shape = MaterialTheme.shapes.small)
                    )

                    Column {
                        Text(text = tweet.name, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "@${tweet.screenName} • ${tweet.time}")
                    }
                }

                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { /* Handle click on visibility icon */ }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = tweet.tweet)

            if (tweet.mediaRes != 0) {
                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = painterResource(id = tweet.mediaRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(shape = MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    TweetStat(icon = Icons.Default.Face , count = tweet.retweetCount)
                    Spacer(modifier = Modifier.width(4.dp))
                    TweetStat(icon = Icons.Default.Favorite, count = tweet.favoriteCount)
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.MailOutline,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { /* Handle click on mail icon */ }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Outlined.Send,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { /* Handle click on send icon */ }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Outlined.ThumbUp,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { /* Handle click on thumb up icon */ }
                    )
                }
            }
        }
    }
}

@Composable
fun TweetStat(icon: ImageVector  , count: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(imageVector = icon , contentDescription = null)
        Text(text = count.toString())
    }
}

@Composable
fun TweetScreen(lifecycleOwner: LifecycleOwner , viewModel: TweetViewModel)
{
val list by viewModel.dataList.observeAsState(emptyList())
TwitterFeed(feedItems = list)
viewModel.loadData()
}