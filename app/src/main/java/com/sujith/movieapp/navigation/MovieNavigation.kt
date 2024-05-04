package com.sujith.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sujith.movieapp.screens.home.HomeScreen
import com.sujith.movieapp.ui.theme.MovieAppTheme

@Composable
@Preview
fun MovieNavigation(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {
        composable(MovieScreens.HomeScreen.name){
            MovieAppTheme {
                HomeScreen(navController = navController)
            }
        }
    }
}