package ru.zhogin.composeClientApp.compose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.compose.client.DataTableListItem
import ru.zhogin.composeClientApp.dto.ClientInfo
import ru.zhogin.composeClientApp.dto.GenderType
import ru.zhogin.composeClientApp.services.MyUtils
import ru.zhogin.composeClientApp.ui.theme.Brize
import ru.zhogin.composeClientApp.ui.theme.Orange
import ru.zhogin.composeClientApp.ui.theme.PurpleGrey40
import ru.zhogin.composeClientApp.viewmodel.ClientViewModule



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ClientFullInfoScreen(
    clientViewModule: ClientViewModule = hiltViewModel(),
    onNavigationBack: () -> Unit,
) {
    clientViewModule.editedClient.value?.let { editedClient ->
        val client = editedClient

        val listOfVisits = client.visits.asReversed()
        val listOfWorks = client.works.asReversed()
        val listOfPrices = client.prices.asReversed()
        val listOfTips = client.tips.asReversed()
//        val listOfDurations = client.durations.asReversed()
        val listOfNotes = client.notes.asReversed()

        val dataList = mutableListOf<ClientInfo>()

        for (i in 0..(listOfVisits.size - 1)) {
            dataList.add(
                ClientInfo(
                    visit = listOfVisits[i],
                    work = listOfWorks[i],
                    prices = listOfPrices[i],
                    tips = listOfTips[i],
//                    durations = listOfDurations[i],
                    notes = listOfNotes[i],
                )
            )

        }
        val cellWidth: (Int) -> Dp = { index ->
            when (index) {
                0 -> 100.dp
                1 -> 150.dp
                4 -> 150.dp
                else -> 100.dp
            }
        }
        val headerCellTitle: @Composable (Int) -> Unit = { index ->
            val value = when (index) {
                0 -> stringResource(id = R.string.visit)
                1 -> stringResource(id = R.string.work)
                2 -> stringResource(id = R.string.price)
                3 -> stringResource(id = R.string.tips)
//                4 -> stringResource(id = R.string.duration)
                4 -> stringResource(id = R.string.note)
                else -> ""
            }
            Text(
                text = value,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Black,
                textDecoration = TextDecoration.Underline,
            )
        }
        val cellText: @Composable (Int, ClientInfo) -> Unit = { index, item ->
            val value = when (index) {
                0 -> item.visit
                1 -> item.work
                2 -> MyUtils.priceToDouble(item.prices).toString()
                3 -> MyUtils.priceToDouble(item.tips).toString()
//                4 -> MyUtils.durationToHour(item.durations).toString()
                4 -> item.notes
                else -> ""
            }
            Text(
                text = value,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        val fullNameText =
            if (!client.patronymicSurname.isNullOrBlank()) client.surname + " " + client.name + " " + client.patronymicSurname
            else client.surname + " " + client.name
        val totalPrice = MyUtils.priceToDouble(listOfPrices.sumOf { it })
//        val estimatedTime = MyUtils.durationToHour(listOfDurations.sumOf { it })
        val totalTips = MyUtils.priceToDouble(listOfTips.sumOf { it })
//        val df = DecimalFormat("#.#")
//        val costOfHour = df.format(totalPrice / estimatedTime)
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        clientViewModule.clearData()
                        onNavigationBack()
                    },
                    backgroundColor = Orange,
                ) {
                    Icon(Icons.Filled.Done, contentDescription = "Add")
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            backgroundColor = PurpleGrey40,

            ) {
            Card(
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize(),
                border = BorderStroke(1.dp, Color.Black)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Brize)
                ) {
                    Text(
                        text = fullNameText,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(14.dp)

                    )
                    if (client.telNumber.isNotBlank()) {
                        Text(
                            text = client.telNumber,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(start = 14.dp, bottom = 14.dp, end = 14.dp)
                        )
                    }
                    if (!client.dateOfBirth.isNullOrBlank()) {
                        Text(
                            text = client.dateOfBirth.toString(),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(start = 14.dp, bottom = 14.dp, end = 14.dp)
                        )
                    }
                    Text(
                        text = if (client.gender == GenderType.MALE) stringResource(id = R.string.male)
                        else stringResource(id = R.string.female),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 14.dp, bottom = 14.dp, end = 14.dp)
                    )
                    if (client.visits.isNotEmpty()) {


                        DataTableListItem(
                            columnCount = 5,
                            cellWidth = cellWidth,
                            data = dataList,
                            headerCellContent = headerCellTitle,
                            cellContent = cellText
                        )


                        Text(
                            text = stringResource(id = R.string.total_price_earned_from_client) + " $totalPrice",
                            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp, end = 8.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.total_tips_earned_from_client) + " $totalTips",
                            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp, end = 8.dp)
                        )
//                        Text(
//                            text = stringResource(id = R.string.estimated_time_spent_on_client) + " $estimatedTime",
//                            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp, end = 8.dp)
//                        )
//                        Text(
//                            text = stringResource(id = R.string.cost_an_hour_of_work_on_this_client) + " $costOfHour",
//                            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp, end = 8.dp)
//                        )

                    }
                }
            }
        }


    }

}