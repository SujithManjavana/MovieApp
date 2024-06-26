package com.sujith.movieapp.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sujith.movieapp.model.Movie
import com.sujith.movieapp.model.getMovies
import com.sujith.movieapp.navigation.MovieScreens
import com.sujith.movieapp.widgets.MovieItem

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = { MyTopAppBar() }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            MainContent(navController)
        }
    }
}

@Composable
fun MainContent(
    navController: NavController,
    movies: List<Movie> = getMovies()
) {
    Column {
        LazyColumn {
            items(items = movies) {
                MovieItem(movie = it) {
                    navController.navigate(route = MovieScreens.DetailsScreen.name+"/$it")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = { Text(text = "Movie App") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
        )
    )
}


