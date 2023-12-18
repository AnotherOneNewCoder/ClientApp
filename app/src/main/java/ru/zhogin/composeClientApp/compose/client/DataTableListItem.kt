package ru.zhogin.composeClientApp.compose.client

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.zhogin.composeClientApp.ui.theme.Brize

@Composable
fun <T> DataTableListItem(
    columnCount: Int,
    cellWidth: (index: Int) -> Dp,
    data: List<T>,
    modifier: Modifier = Modifier,
    headerCellContent: @Composable (index: Int) -> Unit,
    //cellContent: @Composable (item: String) -> Unit,
    cellContent: @Composable (index: Int, item: T) -> Unit,
) {
    Surface(modifier = modifier) {
        LazyRow(
            modifier = Modifier
                .background(Brize)
                .padding(12.dp)
        ) {
            items((0 until columnCount).toList()) { columnIndex ->
                Column {
                    (0..data.size).forEach { index ->
                        Surface(
                            border = BorderStroke(1.dp, Color.Black),
                            contentColor = Color.Transparent,
                            color = Brize,
                            modifier = Modifier.width(cellWidth(columnIndex)),
                        )
                        {
                            if (index == 0) {
                                headerCellContent(columnIndex)
                            } else {
                                cellContent(columnIndex, data[index - 1])
                            }
                        }
                    }
                }
            }
        }
    }
}