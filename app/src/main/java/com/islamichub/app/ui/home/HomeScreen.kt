package com.islamichub.app.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import com.islamichub.app.ui.theme.IHColors

data class FeatureItem(val title: String, val icon: String, val color: Long, val route: String)

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    val features = listOf(
        FeatureItem("আসমাউল হুসনা", "💚", 0xFF0B5E42, "asmaul"),
        FeatureItem("হাদিস", "📜", 0xFF2D6A4F, "hadith"),
        FeatureItem("কালিমা", "📿", 0xFF40916C, "kalima"),
        FeatureItem("জিকর কাউন্টার", "🤍", 0xFFC49A2B, "zikr"),
        FeatureItem("কিবলা কম্পাস", "🧭", 0xFF1A3329, "qibla"),
        FeatureItem("ইসলামিক গল্প", "📚", 0xFF073D2A, "stories"),
        FeatureItem("প্রশ্নোত্তর", "❓", 0xFF40916C, "questions"),
        FeatureItem("ভ্রান্ত ধারণা", "⚠️", 0xFFDC2626, "misconceptions")
    )

    Column(
        Modifier.fillMaxSize().background(IHColors.BG).padding(16.dp)
    ) {
        Text("ইসলামিক জ্ঞান", color = IHColors.White, fontSize = 24.sp, fontWeight = FontWeight.Black)
        Text("সম্পূর্ণ ইসলামিক জ্ঞানকোষ", color = IHColors.TextSecondary, fontSize = 12.sp)
        Spacer(Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(features) { feature ->
                FeatureCard(feature) { onNavigate(feature.route) }
            }
        }
    }
}

@Composable
private fun FeatureCard(feature: FeatureItem, onClick: () -> Unit) {
    Box(
        Modifier.clip(RoundedCornerShape(16.dp))
            .background(Brush.verticalGradient(listOf(IHColors.Surface, IHColors.SurfaceLight)))
            .border(1.dp, IHColors.Divider, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Column {
            Text(feature.icon, fontSize = 32.sp)
            Spacer(Modifier.height(8.dp))
            Text(feature.title, color = IHColors.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}

