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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
    val note = rememberSaveable {
        mutableStateOf("")
    }
    val listClient = sharedClientViewModel.data.collectAsState(initial = emptyList())

    var clientPhoto by rememberSaveable {
        mutableStateOf("")
    }
    var noteChanged by rememberSaveable {
        mutableStateOf(false)
    }
    var positionInList by rememberSaveable {
        mutableIntStateOf(Int.MAX_VALUE)
    }
    var noteOrWork by rememberSaveable {
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
                    sharedClientViewModel.clearData()
                    navHostController.navigate(NavigationScreens.CalendarDayAndEventsScreen.route)
                },
                onNavigationSuccessfulCalendarDayEvent = {
                    navHostController.navigate(NavigationScreens.SuccessfulCalendarDayEvent.route)
                },
            )
        }
        composable(BottomItem.ScreenSecond.route) {
            UsersScreen(
                onNavigationNewClient = {
                    sharedClientViewModel.clearData()
                    navHostController.navigate(NavigationScreens.NewClient.route)
                },
                onNavigationAvatarFullSize = {
                    clientPhoto = it
                    navHostController.navigate(NavigationScreens.AvatarClientFullScreen.route)
                },
                onNavigationEditClient = { client ->
                    sharedClientViewModel.clearData()
                    sharedClientViewModel.editedClient.value =
                        sharedClientViewModel.editedClient.value?.copy(
                            id = client.id,
                            telNumber = client.telNumber,
                            photo = client.photo,
                            name = client.name,
                            surname = client.surname,
                            patronymicSurname = client.patronymicSurname,
                            dateOfBirth = client.dateOfBirth,
                            gender = client.gender,
                            visits = client.visits,
                            works = client.works,
                            prices = client.prices,
                            tips = client.tips,
                            notes = client.notes,
                            durations = client.durations,

                            )
                    navHostController.navigate(NavigationScreens.NewClient.route)
                },
                onNavigationClientFullInfoScreen = { client ->
                    sharedClientViewModel.clearData()
                    sharedClientViewModel.editedClient.value =
                        sharedClientViewModel.editedClient.value?.copy(
                            id = client.id,
                            telNumber = client.telNumber,
                            photo = client.photo,
                            name = client.name,
                            surname = client.surname,
                            patronymicSurname = client.patronymicSurname,
                            dateOfBirth = client.dateOfBirth,
                            gender = client.gender,
                            visits = client.visits,
                            works = client.works,
                            prices = client.prices,
                            tips = client.tips,
                            notes = client.notes,
                            durations = client.durations,

                            )
                    navHostController.navigate(NavigationScreens.ClientFullInfoScreen.route)
                },
                clientsList = listClient.value,
                onRemoveById = { sharedClientViewModel.removeClientById(it) }
            )
        }
        composable(BottomItem.ScreenThird.route) {
            NotesScreen()
        }
        composable(BottomItem.ScreenFourth.route) {
            SettingScreen()
        }
        composable(NavigationScreens.NewClient.route) {
            sharedClientViewModel.editedClient.value?.let { editClient ->
                NewClientScreen(
                    onNavigation = { client ->
                        sharedClientViewModel.newSaveClient(client)
                        sharedClientViewModel.clearData()
                        navHostController.navigate(BottomItem.ScreenSecond.route)
                    },
                    client = editClient
                )
            }
        }
        composable(
            route = NavigationScreens.AvatarClientFullScreen.route

        ) {

            ClientAvatarFullSizeScreen(
                onNavigationUsersScreen = {
                    navHostController.navigate(BottomItem.ScreenSecond.route)
                },
                clientPhoto = clientPhoto,
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
                listClient = listClient.value,
                onNavigateUp = { client ->
                    sharedClientViewModel.clearData()
                    sharedClientViewModel.editedClient.value =
                        sharedClientViewModel.editedClient.value?.copy(
                            id = client.id,
                            telNumber = client.telNumber,
                            photo = client.photo,
                            name = client.name,
                            surname = client.surname,
                            patronymicSurname = client.patronymicSurname,
                            dateOfBirth = client.dateOfBirth,
                            gender = client.gender,
                            visits = client.visits,
                            works = client.works,
                            prices = client.prices,
                            tips = client.tips,
                            notes = client.notes,
                            durations = client.durations,

                            )
                    navHostController.navigateUp()
                }
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
            sharedClientViewModel.editedClient.value?.let { client ->
                ClientFullInfoScreen(
                    client = client,
                    onNavigationBack = {
                        noteChanged = false
                        //sharedClientViewModel.newSaveClient(it)
                        navHostController.navigate(BottomItem.ScreenSecond.route)
                        sharedClientViewModel.clearData()
                    },
                    onNavigationShowNote = { text, position, workOrNote ->
                        note.value = text
                        positionInList = position
                        noteOrWork = workOrNote
                        navHostController.navigate(NavigationScreens.ShowClientNotes.route)
                    },
                    mutableText = note.value,
                    changeNote = noteChanged,
                    changeNotePosition = positionInList,
                    workOrNote = noteOrWork,
                    onSaveChanges = {
                        sharedClientViewModel.newSaveClient(it)
                        noteChanged = false
                    }
                )
            }
        }
        composable(NavigationScreens.ShowClientNotes.route) {
            ShowClientNotes(
                text = note.value,
                onNavigationBack = { text ->
                    if (note.value != text) {
                        note.value = text
                        noteChanged = true
                    } else {
                        noteChanged = false
                    }
                    navHostController.navigate(NavigationScreens.ClientFullInfoScreen.route)
                }
            )
        }
    }
}


