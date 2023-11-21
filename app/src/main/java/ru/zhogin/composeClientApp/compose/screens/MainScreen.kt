package ru.zhogin.composeClientApp.compose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ru.zhogin.composeClientApp.compose.bottomnav.BottomNavigationView
import ru.zhogin.composeClientApp.compose.bottomnav.NavGraph
import ru.zhogin.composeClientApp.ui.theme.MyTransperent


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.background(MyTransperent),
        bottomBar = {
            BottomNavigationView(navController = navController)
        }
    ) {
        NavGraph(navHostController = navController)
    }
}