package com.yakisan.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.yakisan.calculator.R
import com.yakisan.calculator.core.getTextTheme
import com.yakisan.calculator.extension.Extension.noRippleClickable
import com.yakisan.calculator.ui.theme.dimens

@Composable
fun AppBar(
    historyOnClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.dimens.medium1,
                vertical = MaterialTheme.dimens.small3
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Calculator", color = getTextTheme(), style = MaterialTheme.typography.headlineLarge)
        Icon(
            modifier = Modifier
                .size(MaterialTheme.dimens.large / 2.8f)
                .noRippleClickable {
                   historyOnClick()
                },
            painter = painterResource(id = R.drawable.ic_history),
            contentDescription = "",
            tint = getTextTheme()
        )
    }


}