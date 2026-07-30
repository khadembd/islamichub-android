package com.islamichub.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.islamichub.app.data.DataRepository
import com.islamichub.app.ui.asmaul.AsmalHusnaScreen
import com.islamichub.app.ui.asmaul.KalimaScreen
import com.islamichub.app.ui.dua.DuaScreen
import com.islamichub.app.ui.hadith.HadithScreen
import com.islamichub.app.ui.home.*
import com.islamichub.app.ui.namaz.NamazScreen
import com.islamichub.app.ui.qibla.QiblaScreen
import com.islamichub.app.ui.splash.SplashScreen
import com.islamichub.app.ui.theme.IHColors
import com.islamichub.app.ui.theme.IslamicHubTheme
import com.islamichub.app.ui.zikr.ZikrCounterScreen
import com.islamichub.app.util.Utils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            IslamicHubTheme {
                AppRoot()
            }
        }
    }
}

@Composable
private fun AppRoot() {
    var showSplash by remember { mutableStateOf(true) }
    var currentTab by remember { mutableStateOf(NavTab.HOME) }
    var featureRoute by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val repo = remember { Utils.getRepo(context) }

    if (showSplash) {
        SplashScreen(onFinished = { showSplash = false })
        return
    }

    // If a feature route is selected, show that screen
    if (featureRoute != null) {
        FeatureScreen(route = featureRoute!!, repo = repo, onBack = { featureRoute = null })
        return
    }

    // Main app with bottom nav
    Column(Modifier.fillMaxSize().background(IHColors.BG)) {
        // Content
        Box(Modifier.weight(1f)) {
            AnimatedContent(targetState = currentTab, label = "tab") { tab ->
                when (tab) {
                    NavTab.HOME -> HomeScreen(onNavigate = { featureRoute = it })
                    NavTab.QURAN -> QuranPlaceholder()
                    NavTab.NAMAZ -> NamazScreen()
                    NavTab.DUA -> DuaScreen(repo)
                    NavTab.MORE -> MorePlaceholder()
                }
            }
        }
        // Bottom nav
        BottomNav(currentTab) { currentTab = it }
    }
}

@Composable
private fun FeatureScreen(route: String, repo: DataRepository, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().background(IHColors.BG)) {
        Row(
            Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("← পিছনে", color = IHColors.Primary, fontSize = 14.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onBack() }.padding(8.dp))
        }
        when (route) {
            "asmaul" -> AsmalHusnaScreen(repo)
            "hadith" -> HadithScreen(repo)
            "kalima" -> KalimaScreen(repo)
            "zikr" -> ZikrCounterScreen()
            "qibla" -> QiblaScreen()
            else -> Text("Coming soon", color = IHColors.White, modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
private fun BottomNav(current: NavTab, onSelect: (NavTab) -> Unit) {
    Row(
        Modifier.fillMaxWidth().background(IHColors.Surface)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NavTab.entries.forEach { tab ->
            val selected = current == tab
            Column(
                Modifier.clip(RoundedCornerShape(12.dp))
                    .clickable { onSelect(tab) }
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(tab.icon, fontSize = 20.sp)
                Text(tab.label, color = if (selected) IHColors.Primary else IHColors.TextTertiary,
                    fontSize = 9.sp, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
            }
        }
    }
}

@Composable
private fun QuranPlaceholder() {
    Box(Modifier.fillMaxSize().background(IHColors.BG), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("📖", fontSize = 48.sp)
            Spacer(Modifier.height(8.dp))
            Text("কুরআন মডিউল", color = IHColors.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text("শীঘ্রই আসছে", color = IHColors.TextSecondary, fontSize = 12.sp)
        }
    }
}

@Composable
private fun MorePlaceholder() {
    Box(Modifier.fillMaxSize().background(IHColors.BG), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("⚙️", fontSize = 48.sp)
            Spacer(Modifier.height(8.dp))
            Text("আরও অপশন", color = IHColors.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text("শীঘ্রই আসছে", color = IHColors.TextSecondary, fontSize = 12.sp)
        }
    }
}
