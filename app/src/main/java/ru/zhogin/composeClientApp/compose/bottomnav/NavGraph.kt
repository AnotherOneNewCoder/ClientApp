package ru.zhogin.composeClientApp.compose.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.zhogin.composeClientApp.compose.screens.CalendarScreen
import ru.zhogin.composeClientApp.compose.screens.ClientAvatarFullSizeScreen
import ru.zhogin.composeClientApp.compose.screens.NewClientScreen
import ru.zhogin.composeClientApp.compose.screens.NotesScreen
import ru.zhogin.composeClientApp.compose.screens.SettingScreen
import ru.zhogin.composeClientApp.compose.screens.UsersScreen
import ru.zhogin.composeClientApp.navigation.NavigationScreens


@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "calendar") {
        composable("calendar") {
            CalendarScreen()
        }
        composable("users") {
            UsersScreen(
                onNavigationNewClient = {
                    navHostController.navigate(NavigationScreens.NewClient.route)
                },
                onNavigationAvatarFullSize = {
                    navHostController.navigate(NavigationScreens.AvatarClientFullScreen.route)
                }
            )
        }
        composable("notes") {
            NotesScreen()
        }
        composable("settings") {
            SettingScreen()
        }
        composable(NavigationScreens.NewClient.route) {
            NewClientScreen {
                navHostController.navigate("users")
            }
        }
        composable(
            route = NavigationScreens.AvatarClientFullScreen.route

        ) {

            ClientAvatarFullSizeScreen(

                onNavigationUsersScreen = {
                    navHostController.navigate("users")
                }
            )
        }
    }
}


