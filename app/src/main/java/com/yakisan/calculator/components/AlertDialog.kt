package com.yakisan.calculator.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.yakisan.calculator.ui.theme.DarkColor
import com.yakisan.calculator.ui.theme.White

@Composable
fun AlertDialog(
    title: String,
    description: String,
    icon: Int,
    buttonTitle: String,
    buttonClicked: () -> Unit,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        CustomDialogUI(title = title, description = description, icon =icon , buttonTitle = buttonTitle, buttonClicked = buttonClicked, showDialog = showDialog)
    }
}

//Layout
@Composable
fun CustomDialogUI(
    title: String,
    description: String,
    icon: Int,
    buttonTitle: String,
    showDialog: Boolean,
    buttonClicked: () -> Unit,
) {
    val darkTheme = isSystemInDarkTheme()
    if (showDialog) {
        Card(
            shape = RoundedCornerShape(12),
            modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        ) {
            Column(
                modifier = Modifier
                    .background(if(darkTheme) DarkColor else Color.White)
            ) {

                //Icon Side
                Box(
                    modifier = Modifier
                        .padding(top = 30.dp, start = 30.dp, end = 30.dp)
                        .background(color = if(darkTheme) DarkColor else Color.White, shape = RoundedCornerShape(50))
                        .align(Alignment.CenterHorizontally)
                ) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = "Alert Dialog",
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(
                            color = if(darkTheme) White else DarkColor
                        ),
                        modifier = Modifier
                            .padding(50.dp)
                    )
                }

                //Title & Description Container
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.headlineMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = description,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                //Custom Button
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(darkTheme) White else DarkColor
                    ),
                    //Buton islevi
                    onClick = buttonClicked
                ) {
                    Text(
                        text = buttonTitle,
                        color = if(darkTheme) DarkColor else White ,
                        modifier = Modifier.padding(10.dp),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}