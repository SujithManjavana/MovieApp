package com.sujith.movieapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.sujith.movieapp.model.Movie
import com.sujith.movieapp.model.getMovies
import com.sujith.movieapp.widgets.MovieItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
    val movie: Movie = getMovies().filter { i -> i.id == movieId }[0]
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Movie details") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
            ),
            scrollBehavior = scrollBehavior,
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
    }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                MovieItem(movie = movie)
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider()
                HorizontalImageScrollable(movie)

            }
        }
    }
}

@Composable
private fun HorizontalImageScrollable(movie: Movie) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .width(240.dp)
                    .height(120.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = "",

                    )
            }
        }
    }
}