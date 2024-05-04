package com.sujith.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sujith.movieapp.screens.details.DetailsScreen
import com.sujith.movieapp.screens.home.HomeScreen
import com.sujith.movieapp.ui.theme.MovieAppTheme

@Composable
@Preview
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {
        composable(MovieScreens.HomeScreen.name) {
            MovieAppTheme {
                HomeScreen(navController = navController)
            }
        }

        composable(
            MovieScreens.DetailsScreen.name + "/{movie}",
            arguments = listOf(navArgument(name = "movie") {
                type =
                    NavType.StringType
                defaultValue = "Some movie"
            })
        ) { backStackEntry ->
            MovieAppTheme {
                DetailsScreen(
                    navController = navController,
                    backStackEntry.arguments?.getString("movie")
                )
            }
        }

    }
}