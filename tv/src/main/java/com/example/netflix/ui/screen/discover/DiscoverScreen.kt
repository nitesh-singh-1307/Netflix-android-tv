package com.example.netflix.ui.screen.discover

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import coil.compose.AsyncImage
import com.example.netflix.data.entities.discovere.DiscoverData
import com.example.netflix.ui.common.Loading
import com.example.netflix.util.Result

@Composable
fun DiscoverScreen(homeScreeViewModel: DiscoverDataViewModel = hiltViewModel()) {
    val movieDetailsState by homeScreeViewModel.movieDetails.collectAsState()
    Log.d("THE", "moviedata:::$movieDetailsState")
    homeScreeViewModel.doDiscoverData()
    when (movieDetailsState) {
        is Result.Loading -> {
            Loading(modifier = Modifier.fillMaxSize())
        /* Show loading indicator */ }
        is Result.Success -> {
            val movieDiscover = (movieDetailsState as Result.Success<List<DiscoverData>>).data
            Log.d("THE", "discoverData:::$movieDiscover")
            DiscoverScreenContent(movieDiscover)
        // ... display other details
        }
        is Result.Error -> {}
    }
}

@Composable
fun DiscoverScreenContent(movieDiscover: List<DiscoverData>) {
    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TvLazyRow(modifier = Modifier.fillMaxWidth()) {

                items(movieDiscover){ movie ->
                    MovieCard(movie)
                }
            }
        }

    }
}

@Composable
fun MovieCard(movie: DiscoverData) {
    Card(modifier = Modifier
        .padding(16.dp)
        .width(200.dp),
        shape = RoundedCornerShape(8.dp),
        elevation =  CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Column {
            AsyncImage(
             model =  movie.imgUrl,
                contentDescription = movie.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = movie.category,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
        }

    }

}



