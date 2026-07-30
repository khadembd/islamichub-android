package com.islamichub.app.ui.zikr

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.islamichub.app.ui.theme.IHColors

data class Zikr(val name: String, val arabic: String, val target: Int)

@Composable
fun ZikrCounterScreen() {
    val zikrList = listOf(
        Zikr("সুবহানআল্লাহ", "سُبْحَانَ اللَّهِ", 33),
        Zikr("আলহামদুলিল্লাহ", "الْحَمْدُ لِلَّهِ", 33),
        Zikr("আল্লাহু আকবার", "اللَّهُ أَكْبَرُ", 34),
        Zikr("লা ইলাহা ইল্লাল্লাহ", "لَا إِلٰهَ إِلَّا اللَّهُ", 100)
    )
    var selectedIdx by remember { mutableStateOf(0) }
    var count by remember { mutableStateOf(0) }
    val zikr = zikrList[selectedIdx]
    val progress = if (zikr.target > 0) count.toFloat() / zikr.target else 0f

    Column(
        Modifier.fillMaxSize().background(IHColors.BG).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("জিকর কাউন্টার", color = IHColors.White, fontSize = 20.sp, fontWeight = FontWeight.Black)
        Spacer(Modifier.height(16.dp))
        // Zikr selector
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            zikrList.forEachIndexed { i, z ->
                Box(
                    Modifier.weight(1f).clip(RoundedCornerShape(8.dp))
                        .background(if (i == selectedIdx) IHColors.Primary else IHColors.Surface)
                        .clickable { selectedIdx = i; count = 0 }.padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(z.name, color = IHColors.White, fontSize = 9.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
        Spacer(Modifier.height(32.dp))
        // Counter circle
        Box(
            Modifier.size(200.dp).clip(CircleShape)
                .background(IHColors.Surface)
                .clickable {
                    count++
                    if (count > zikr.target) count = 0
                },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(zikr.arabic, color = IHColors.White, fontSize = 24.sp)
                Spacer(Modifier.height(8.dp))
                Text("$count", color = IHColors.Gold, fontSize = 48.sp, fontWeight = FontWeight.Black)
                Text("/ ${zikr.target}", color = IHColors.TextSecondary, fontSize = 14.sp)
            }
        }
        Spacer(Modifier.height(16.dp))
        Text("ট্যাপ করুন গণনা করতে", color = IHColors.TextTertiary, fontSize = 11.sp)
        Spacer(Modifier.height(16.dp))
        // Progress bar
        LinearProgressIndicator(
            progress = progress,
            color = IHColors.Primary,
            trackColor = IHColors.SurfaceLight,
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(4.dp))
        )
        Spacer(Modifier.height(8.dp))
        // Reset button
        Text("রিসেট", color = IHColors.Error, fontSize = 12.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { count = 0 }.padding(8.dp))
    }
}
