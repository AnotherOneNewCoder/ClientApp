package ru.zhogin.composeClientApp.compose.bottomnav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
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
import ru.zhogin.composeClientApp.viewmodel.ClientViewModule


@Composable
fun NavGraph(
    navHostController: NavHostController,

) {
    val sharedClientViewModel: ClientViewModule = hiltViewModel()
    NavHost(navController = navHostController, startDestination = "calendar") {
        composable("calendar") {
            CalendarScreen()
        }
        composable("users") {
            UsersScreen(
                clientViewModule = sharedClientViewModel,
                onNavigationNewClient = {
                    navHostController.navigate(NavigationScreens.NewClient.route)
                },
                onNavigationAvatarFullSize = {
                    navHostController.navigate(NavigationScreens.AvatarClientFullScreen.route)
                },
                onNavigationEditClient = {
                    navHostController.navigate(NavigationScreens.NewClient.route)
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
            NewClientScreen (
                clientViewModule = sharedClientViewModel,
                onNavigation = {navHostController.navigate("users")
            })
        }
        composable(
            route = NavigationScreens.AvatarClientFullScreen.route

        ) {

            ClientAvatarFullSizeScreen(
                clientViewModule = sharedClientViewModel,
                onNavigationUsersScreen = {
                    navHostController.navigate("users")
                }
            )
        }
    }
}


