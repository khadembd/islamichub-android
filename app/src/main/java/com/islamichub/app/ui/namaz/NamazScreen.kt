package com.islamichub.app.ui.namaz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.islamichub.app.ui.theme.IHColors

@Composable
fun NamazScreen() {
    val namazTypes = listOf(
        "ফজর" to "২ সুন্নত + ২ ফরজ",
        "যোহর" to "৪ সুন্নত + ৪ ফরজ + ২ সুন্নত",
        "আসর" to "৪ সুন্নত + ৪ ফরজ",
        "মাগরিব" to "৩ ফরজ + ২ সুন্নত",
        "এশা" to "৪ সুন্নত + ৪ ফরজ + ২ সুন্নত + ৩ বিতর"
    )

    Column(Modifier.fillMaxSize().background(IHColors.BG)) {
        Text("নামাজ শিক্ষা", color = IHColors.White, fontSize = 20.sp, fontWeight = FontWeight.Black,
            modifier = Modifier.padding(16.dp))
        Text("৫ ওয়াক্ত নামাজের রাকাত", color = IHColors.TextSecondary, fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(8.dp))
        LazyColumn(contentPadding = PaddingValues(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(namazTypes) { (name, rakats) ->
                Box(
                    Modifier.clip(RoundedCornerShape(12.dp))
                        .background(IHColors.Surface)
                        .padding(16.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text("🕌", fontSize = 24.sp)
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(name, color = IHColors.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(rakats, color = IHColors.TextSecondary, fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}
