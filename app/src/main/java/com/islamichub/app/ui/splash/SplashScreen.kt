package com.islamichub.app.ui.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.islamichub.app.ui.theme.IHColors
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    var stage by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        delay(300); stage = 1
        delay(1200); stage = 2
        delay(300); onFinished()
    }

    val scale by animateFloatAsState(
        targetValue = if (stage >= 1) 1f else 0.5f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "scale"
    )
    val alpha by animateFloatAsState(
        targetValue = if (stage >= 1) 1f else 0f,
        animationSpec = tween(600),
        label = "alpha"
    )
    val ringRotation by rememberInfiniteTransition(label = "ring").animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing)),
        label = "rotation"
    )

    Box(
        Modifier.fillMaxSize().background(IHColors.BG),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Mosque emoji as logo (no external image needed)
            Text("🕌", fontSize = 72.sp, modifier = Modifier.scale(scale).alpha(alpha))
            Spacer(Modifier.height(16.dp))
            Text(
                "ইসলামিক জ্ঞান Hub",
                color = IHColors.White, fontSize = 24.sp, fontWeight = FontWeight.Black,
                modifier = Modifier.scale(scale).alpha(alpha)
            )
            Spacer(Modifier.height(8.dp))
            if (stage >= 2) {
                Text("প্রবেশ করছি...", color = IHColors.Primary, fontSize = 12.sp,
                    modifier = Modifier.alpha(alpha))
            }
        }
    }
}
