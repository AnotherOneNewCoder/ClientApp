package ru.zhogin.composeClientApp.compose.bottomnav

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.zhogin.composeClientApp.ui.theme.MyWhite2
import ru.zhogin.composeClientApp.ui.theme.PurpleGrey40


@Composable
fun BottomNavigationView(
    navController: NavController,

    ) {
    val listItems = listOf(
        BottomItem.ScreenFirst,
        BottomItem.ScreenSecond,
        BottomItem.ScreenThird,
        BottomItem.ScreenFourth,
    )
    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.Black
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                icon = { Icon(
                    painter = painterResource(id = item.icon), contentDescription = "Icon"
                ) },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 12.sp,
                    )
                },
                selectedContentColor = MyWhite2,
                unselectedContentColor = PurpleGrey40,

            )
        }
    }

}