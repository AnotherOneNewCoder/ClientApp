package ru.zhogin.composeClientApp.compose.floatingbutton

import android.annotation.SuppressLint
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FabFromAnyWhere(
    fabPosition: FabPosition,
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        floatingActionButtonPosition = fabPosition,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClick,
                modifier = modifier,
                content = content
            )
        }
    ) {}
}
