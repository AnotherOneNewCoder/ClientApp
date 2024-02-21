package ru.zhogin.composeClientApp.compose.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.zhogin.composeClientApp.compose.bottomnav.BottomItem
import ru.zhogin.composeClientApp.compose.bottomnav.BottomNavigationView
import ru.zhogin.composeClientApp.compose.bottomnav.NavGraph
import ru.zhogin.composeClientApp.ui.theme.MyTransperent



@RequiresApi(Build.VERSION_CODES.P)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val listItems = listOf(
        BottomItem.ScreenFirst,
        BottomItem.ScreenSecond,
        BottomItem.ScreenThird,
        BottomItem.ScreenFourth,
    )
    val showBottomBar = navController.currentBackStackEntryAsState().value?.destination?.route in listItems.map { it.route }
    Scaffold(
        modifier = Modifier.background(MyTransperent),
        bottomBar = {
            if (showBottomBar) {
            BottomNavigationView(navController = navController)
        }
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(padding)
        ) {
            NavGraph(navHostController = navController)
        }

    }
}