package ru.zhogin.composeClientApp.navigation

sealed class NavigationScreens(val route: String) {
    object NewClient: NavigationScreens("new_client_screen")
    object AvatarClientFullScreen: NavigationScreens("avatar_client_full_screen")
    object CalendarDayAndEventsScreen: NavigationScreens("calendar_day_and_events_screen")
}
