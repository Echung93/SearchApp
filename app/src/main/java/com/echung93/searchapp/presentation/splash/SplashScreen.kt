package com.echung93.searchapp.presentation.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.echung93.searchapp.R

@Composable
fun SplashScreen() {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val offsetState by animateDpAsState(
        targetValue =
        if (startAnimation) 0.dp else 100.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    val alphaState by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000L)
    }

    Splash(offsetState = offsetState, alphaState = alphaState)
}

@Composable
fun Splash(offsetState: Dp,
           alphaState: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(100.dp)
                .offset(y = offsetState)
                .alpha(alpha = alphaState),
            painter = painterResource(id = R.drawable.ic_logo_foreground),
            contentDescription = stringResource(
                id = R.string.splash
            )
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash(offsetState = 0.dp, alphaState = 1f)
}