package ru.zhogin.composeClientApp.compose.screens

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.R.string.work
import ru.zhogin.composeClientApp.compose.client.DataTableListItem
import ru.zhogin.composeClientApp.dto.Client
import ru.zhogin.composeClientApp.dto.ClientInfo
import ru.zhogin.composeClientApp.dto.GenderType
import ru.zhogin.composeClientApp.services.MyUtils
import ru.zhogin.composeClientApp.ui.theme.Brize
import ru.zhogin.composeClientApp.ui.theme.Brize2


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ClientFullInfoScreen(

    client: Client,
    onNavigationBack: () -> Unit,
    onNavigationShowNote: (text: String, position: Int, workOrNote: String) -> Unit,
    mutableText: String,
    changeNote: Boolean,
    changeNotePosition: Int,
    workOrNote: String,
    onSaveChanges: (client: Client) -> Unit,
) {
    val context = LocalContext.current
    val changeText by rememberSaveable {
        mutableStateOf(mutableText)
    }
    var positionInList by rememberSaveable {
        mutableIntStateOf(Int.MAX_VALUE)
    }
    val notesArray by rememberSaveable {
        mutableStateOf(client.notes.asReversed().toTypedArray())
    }
    val worksArray by rememberSaveable {
        mutableStateOf(client.works.asReversed().toTypedArray())
    }
    val listOfVisits = client.visits.asReversed()
   // val listOfWorks = client.works.asReversed().toTypedArray()
    val listOfPrices = client.prices.asReversed()
    val listOfTips = client.tips.asReversed()
//        val listOfDurations = client.durations.asReversed()
    //val listOfNotes = client.notes.asReversed().toTypedArray()

    if (changeNote) {
        if (workOrNote.isNotEmpty()) {
            if (workOrNote == "note") {
                notesArray[changeNotePosition] = changeText
                onSaveChanges(
                    client.copy(
                        notes = notesArray.toList().asReversed()
                    )
                )
                Toast.makeText(context, R.string.note_edited , Toast.LENGTH_SHORT).show()

            }
            else {
                worksArray[changeNotePosition] = changeText
                onSaveChanges(
                    client.copy(
                        works = worksArray.toList().asReversed()
                    )
                )
                Toast.makeText(context, R.string.work_edited , Toast.LENGTH_SHORT).show()
            }
        }
    }


    val dataList = mutableListOf<ClientInfo>()


    for (i in 0..(listOfVisits.size - 1)) {
        dataList.add(
            ClientInfo(
                visit = listOfVisits[i],
                work = worksArray[i],
                prices = listOfPrices[i],
                tips = listOfTips[i],
//                    durations = listOfDurations[i],
                //notes = listOfNotes[i],
                notes = notesArray[i],
                position = i
            )
        )

    }
    val cellWidth: (Int) -> Dp = { index ->
        when (index) {
            0 -> 100.dp
            1 -> 150.dp
            2 -> 150.dp
            else -> 100.dp
        }
    }
    val headerCellTitle: @Composable (Int) -> Unit = { index ->
        val value = when (index) {
            0 -> stringResource(id = R.string.visit)
            1 -> stringResource(id = work)
            3 -> stringResource(id = R.string.price)
            4 -> stringResource(id = R.string.tips)
//                4 -> stringResource(id = R.string.duration)
            2 -> stringResource(id = R.string.note)
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
            2 -> item.notes

            4 -> MyUtils.priceToDouble(item.tips).toString()
//                4 -> MyUtils.durationToHour(item.durations).toString()
            3 -> MyUtils.priceToDouble(item.prices).toString()
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
    val onShowNotes: (Int, ClientInfo) -> Unit = { index, item ->
        when (index) {
            1 -> {
                positionInList = item.position
                onNavigationShowNote(item.work, positionInList, "work")
            }

            2 -> {
                positionInList = item.position
                onNavigationShowNote(item.notes, positionInList, "note")
            }

            else -> Unit
        }
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
                    onNavigationBack(
//                        client.copy(
//                            notes = listOfNotes.toList().asReversed(),
//                            works = listOfWorks.toList().asReversed()
//                        )
                    )
                },
                backgroundColor = Brize2,
            ) {
                Icon(Icons.Filled.Done, contentDescription = "Done", tint = Color.White)
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        backgroundColor = Color.White,

        ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),

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
                        cellContent = cellText,
                        onShowNotes = onShowNotes,
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


    //}

}