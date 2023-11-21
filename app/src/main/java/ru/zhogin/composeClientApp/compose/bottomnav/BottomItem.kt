package ru.zhogin.composeClientApp.compose.bottomnav

import ru.zhogin.composeClientApp.R

sealed class BottomItem (val title: String, val icon: Int, val route: String){
    object ScreenFirst: BottomItem("Calendar", R.drawable.ic_calendar_24, "calendar")
    object ScreenSecond: BottomItem("Data Base", R.drawable.ic_users_24, "users")
    object ScreenThird: BottomItem("Notes", R.drawable.ic_notes_24, "notes")
    object ScreenFourth: BottomItem("Settings", R.drawable.ic_settings, "settings")
}