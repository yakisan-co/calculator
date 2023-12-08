package com.yakisan.calculator.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.yakisan.calculator.core.getTextTheme
import com.yakisan.calculator.core.getTheme
import com.yakisan.calculator.ui.theme.DarkBlue
import com.yakisan.calculator.ui.theme.DarkGray
import com.yakisan.calculator.ui.theme.LightBlue
import com.yakisan.calculator.ui.theme.dimens

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryCard(
    dayOfMonth: String,
    month: String,
    year: String,
    time: String,
    value: String,
    result: String,
) {
    val boxColor = if (getTheme() == DarkBlue) LightBlue else DarkBlue

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.dimens.medium1,
                end = MaterialTheme.dimens.medium1,
                bottom = MaterialTheme.dimens.small1
            )
            .clip(shape = RoundedCornerShape(20))
            .background(color = boxColor.copy(0.1f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(color = getTextTheme().copy(0.1f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(color = getTextTheme().copy(0.1f))
                        .padding(MaterialTheme.dimens.small3),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = dayOfMonth,
                        style = MaterialTheme.typography.headlineLarge,
                        color = getTextTheme()
                    )
                    Text(
                        text = month.substring(0, 3),
                        style = MaterialTheme.typography.bodyMedium,
                        color = getTextTheme()
                    )
                    Text(
                        text = year,
                        style = MaterialTheme.typography.bodyMedium,
                        color = getTextTheme()
                    )
                    Text(
                        text = time,
                        style = MaterialTheme.typography.bodyMedium,
                        color = getTextTheme()
                    )
                }
            }

            Column(
                modifier = Modifier.padding(MaterialTheme.dimens.small3),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = value,
                    color = DarkGray
                )
                Text(
                    text = result,
                    style = MaterialTheme.typography.headlineMedium,
                    color = getTextTheme()
                )

            }


        }

    }
}