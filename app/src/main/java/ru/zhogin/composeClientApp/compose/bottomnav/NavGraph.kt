package ru.zhogin.composeClientApp.compose.bottomnav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.zhogin.composeClientApp.compose.screens.CalendarDayAndEventsScreen
import ru.zhogin.composeClientApp.compose.screens.CalendarScreen
import ru.zhogin.composeClientApp.compose.screens.ClientAvatarFullSizeScreen
import ru.zhogin.composeClientApp.compose.screens.ClientFullInfoScreen
import ru.zhogin.composeClientApp.compose.screens.NewClientScreen
import ru.zhogin.composeClientApp.compose.screens.NotesScreen
import ru.zhogin.composeClientApp.compose.screens.SelectUserScreen
import ru.zhogin.composeClientApp.compose.screens.SettingScreen
import ru.zhogin.composeClientApp.compose.screens.ShowClientNotes
import ru.zhogin.composeClientApp.compose.screens.SuccessfulCalendarDayEvent
import ru.zhogin.composeClientApp.compose.screens.UsersScreen
import ru.zhogin.composeClientApp.navigation.NavigationScreens
import ru.zhogin.composeClientApp.viewmodel.CalendarDayEventViewModel
import ru.zhogin.composeClientApp.viewmodel.CalendarDayViewModel
import ru.zhogin.composeClientApp.viewmodel.ClientViewModule


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun NavGraph(
    navHostController: NavHostController,

    ) {
    val sharedClientViewModel: ClientViewModule = hiltViewModel()
    val sharedClientViewModelForCalendar: ClientViewModule = hiltViewModel()
    val sharedCalendarDayViewModel: CalendarDayViewModel = hiltViewModel()
    val sharedCalendarDayEventViewModel: CalendarDayEventViewModel = hiltViewModel()
    val note = remember {
        mutableStateOf("")
    }


    NavHost(
        navController = navHostController,
        startDestination = BottomItem.ScreenFirst.route,
        enterTransition = {
        fadeIn(
            animationSpec = tween(
                300, easing = LinearEasing
            )
        ) + slideIntoContainer(
            animationSpec = tween(300, easing = EaseIn),
            towards = AnimatedContentTransitionScope.SlideDirection.Start
        )
    },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideOutOfContainer(
                animationSpec = tween(300, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )

        }) {
        composable(BottomItem.ScreenFirst.route) {
            CalendarScreen(
                calendarDayViewModel = sharedCalendarDayViewModel,
                calendarDayEventViewModel = sharedCalendarDayEventViewModel,
                onNavigationCalendarDayAndEventsScreen = {
                    navHostController.navigate(NavigationScreens.CalendarDayAndEventsScreen.route)
                },
                onNavigationSuccessfulCalendarDayEvent = {
                    navHostController.navigate(NavigationScreens.SuccessfulCalendarDayEvent.route)
                },
            )
        }
        composable(BottomItem.ScreenSecond.route) {
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
                },
                onNavigationClientFullInfoScreen = {
                    navHostController.navigate(NavigationScreens.ClientFullInfoScreen.route)
                }
            )
        }
        composable(BottomItem.ScreenThird.route) {
            NotesScreen()
        }
        composable(BottomItem.ScreenFourth.route) {
            SettingScreen()
        }
        composable(NavigationScreens.NewClient.route) {
            NewClientScreen(
                clientViewModule = sharedClientViewModel,
                onNavigation = {
                    navHostController.navigate(BottomItem.ScreenSecond.route)
                })
        }
        composable(
            route = NavigationScreens.AvatarClientFullScreen.route

        ) {

            ClientAvatarFullSizeScreen(
                clientViewModule = sharedClientViewModel,
                onNavigationUsersScreen = {
                    navHostController.navigate(BottomItem.ScreenSecond.route)
                }
            )
        }
        composable(NavigationScreens.CalendarDayAndEventsScreen.route) {
            CalendarDayAndEventsScreen(
                clientViewModule = sharedClientViewModelForCalendar,
                calendarDayViewModel = sharedCalendarDayViewModel,
                onNavigationBack = { navHostController.navigateUp() },
                onNavigateToSelectClientScreen = {
                    navHostController.navigate(NavigationScreens.SelectUserScreen.route)
                }
            )
        }
        composable(NavigationScreens.SelectUserScreen.route) {
            SelectUserScreen(
                clientViewModule = sharedClientViewModelForCalendar,
                onNavigateUp = { navHostController.navigateUp() }
            )
        }
        composable(NavigationScreens.SuccessfulCalendarDayEvent.route) {
            SuccessfulCalendarDayEvent(
                calendarDayEventViewModel = sharedCalendarDayEventViewModel,
                onNavigateToCalendarScreen = {
                    navHostController.navigate(BottomItem.ScreenFirst.route)
                }
            )
        }
        composable(NavigationScreens.ClientFullInfoScreen.route) {
            ClientFullInfoScreen(
                clientViewModule = sharedClientViewModel,
                onNavigationBack = {
                    navHostController.navigateUp()
                },
                onNavigationShowNote = {
                    note.value = it
                    navHostController.navigate(NavigationScreens.ShowClientNotes.route)
                }
            )
        }
        composable(NavigationScreens.ShowClientNotes.route) {
            ShowClientNotes(
                text = note.value,
                onNavigationBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}


