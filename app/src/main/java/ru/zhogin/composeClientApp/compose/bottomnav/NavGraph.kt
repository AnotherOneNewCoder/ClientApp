package ru.zhogin.composeClientApp.compose.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.zhogin.composeClientApp.compose.screens.CalendarScreen
import ru.zhogin.composeClientApp.compose.screens.NotesScreen
import ru.zhogin.composeClientApp.compose.screens.SettingScreen
import ru.zhogin.composeClientApp.compose.screens.UsersScreen




@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "calendar") {
        composable("calendar"){
            CalendarScreen()
        }
        composable("users"){
            UsersScreen()
        }
        composable("notes"){
            NotesScreen()
        }
        composable("settings"){
            SettingScreen()
        }
    }
}