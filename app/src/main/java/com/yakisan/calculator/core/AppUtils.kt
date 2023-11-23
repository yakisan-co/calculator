package com.yakisan.calculator.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import com.yakisan.calculator.ui.theme.CompactDimens
import com.yakisan.calculator.ui.theme.Dimens

@Composable
fun ProvideAppUtils(
    dimens: Dimens,
    content: @Composable () -> Unit,
) {
    val appDimens = remember { dimens }
    CompositionLocalProvider(LocalAppDimens provides appDimens) {
        content()
    }
}

val LocalAppDimens = compositionLocalOf {
    CompactDimens
}

val ScreenOrientation
    @Composable
    get() = LocalConfiguration.current.orientation