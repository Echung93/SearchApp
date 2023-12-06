package com.echung93.searchapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.echung93.searchapp.presentation.splash.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun SearchApp() {
    var showSplashScreen by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = showSplashScreen) {
        delay(3000)
        showSplashScreen = false
    }

    Scaffold(
        modifier = Modifier,
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            if(showSplashScreen) SplashScreen()
        }
    }
}