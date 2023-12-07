package ru.zhogin.composeClientApp.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.zhogin.composeClientApp.compose.client.DataClientListItem
import ru.zhogin.composeClientApp.ui.theme.Brize
import ru.zhogin.composeClientApp.ui.theme.Pink40
import ru.zhogin.composeClientApp.viewmodel.ClientViewModule
import java.text.DecimalFormat


@Composable
fun ClientFullInfoScreen(
    clientViewModule: ClientViewModule = hiltViewModel(),
) {
    clientViewModule.editedClient.value?.let { editedClient ->
        val client = editedClient

        val listOfVisits = client.visits
        val listOfWorks = client.works
        val listOfPrices = client.prices
        val listOfTips = client.tips
        val listOfDurations = client.durations
        val listOfNotes = client.notes
        val count = listOfVisits.size
        val fullNameText =
            if (!client.patronymicSurname.isNullOrBlank()) client.surname + " " + client.name + " " + client.patronymicSurname
            else client.surname + " " + client.name
        val totalPrice  = listOfPrices.sumOf { it }
        val estimatedTime  = listOfDurations.sumOf { it }
        val totalTips  = listOfTips.sumOf { it }
        val df = DecimalFormat("#.#")
        val costOfHour = df.format(totalPrice / estimatedTime)

        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brize)
            ) {
                OutlinedTextField(
                    value = fullNameText,
                    onValueChange = {},
                    textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
                )
                OutlinedTextField(
                    value = client.telNumber,
                    onValueChange = {},
                    textStyle = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Center),
                )
                OutlinedTextField(
                    value = client.dateOfBirth.toString(),
                    onValueChange = {},
                    textStyle = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Center),
                )
                OutlinedTextField(
                    value = client.gender.name,
                    onValueChange = {},
                    textStyle = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Center),
                )
                if (client.works.isNotEmpty()) {

                    //        Box(contentAlignment = Alignment.TopStart) {

                    LazyRow(
                        modifier = Modifier.padding(top = 8.dp)

                    ) {
                        item {
                            DataClientListItem(
                                visit = "visit", work = "work", price = "price", tips = "tips",
                                duration = "duration", note = "note", width = 85,
                            )
                        }
                        itemsIndexed(listOfWorks) { index, _ ->
                            DataClientListItem(
                                visit = listOfVisits[index],
                                work = listOfWorks[index],
                                price = listOfPrices[index].toString(),
                                tips = listOfTips[index].toString(),
                                duration = listOfDurations[index].toString(),
                                note = listOfNotes[index],
                                width = 110
                            )
                        }

//                    items(count) {
//                        var i = 0
//                        DataClientListItem(
//                            visit = listOfVisits[i],
//                            work = listOfWorks[i],
//                            price = listOfPrices[i].toString(),
//                            tips = listOfTips[i].toString(),
//                            duration = listOfDurations[i].toString(),
//                            note = listOfNotes[i],
//                            width = 110,
//                        )
//                        i +=1
//                    }


                    }
                    Text(text = "Total price earned from client: $totalPrice")
                    Text(text = "Total tips earned from client: $totalTips")
                    Text(text = "Estimated time spent on client: $estimatedTime")
                    Text(text = "Cost an hour of work on this client: $costOfHour")

                }
            }
        }

    }

}