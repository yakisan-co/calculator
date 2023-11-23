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
import com.yakisan.calculator.domain.CalculatorOperation
import com.yakisan.calculator.ui.theme.DarkBlue
import com.yakisan.calculator.ui.theme.DarkGray
import com.yakisan.calculator.ui.theme.LightBlue
import com.yakisan.calculator.ui.theme.dimens
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryCard(
    date: LocalDateTime,
    operation: CalculatorOperation,
    value: String,
    result: String
) {
    val boxColor = if(getTheme() == DarkBlue) LightBlue else DarkBlue

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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(color = getTextTheme().copy(0.1f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(color = getTextTheme().copy(0.5f))
                        .padding(MaterialTheme.dimens.small3),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = date.dayOfMonth.toString(),
                        style = MaterialTheme.typography.headlineLarge,
                        color = getTextTheme()
                    )
                    Text(
                        text = date.month.toString().substring(0, 3),
                        style = MaterialTheme.typography.bodyMedium,
                        color = getTextTheme()
                    )
                    Text(
                        text = date.year.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = getTextTheme()
                    )
                }
            }

            //TODO: CalculationOperation özelliğine göre "Cikarma, carpma islemi etc." gibi yazdirilacak.
            Text(
                text = "İşlemin adı",
                style = MaterialTheme.typography.bodyMedium,
                color = getTextTheme()
            )

            Column(
                modifier = Modifier.padding(MaterialTheme.dimens.small3),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
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