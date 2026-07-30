package com.islamichub.app.ui.asmaul

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
import com.islamichub.app.data.DataRepository
import com.islamichub.app.model.Kalima
import com.islamichub.app.ui.theme.IHColors

@Composable
fun KalimaScreen(repo: DataRepository) {
    var items by remember { mutableStateOf<List<Kalima>>(emptyList()) }
    LaunchedEffect(Unit) { items = repo.loadKalima() }

    Column(Modifier.fillMaxSize().background(IHColors.BG)) {
        Text("কালিমা", color = IHColors.White, fontSize = 20.sp, fontWeight = FontWeight.Black,
            modifier = Modifier.padding(16.dp))
        LazyColumn(contentPadding = PaddingValues(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items) { kalima ->
                Box(
                    Modifier.clip(RoundedCornerShape(12.dp)).background(IHColors.Surface).padding(16.dp)
                ) {
                    Column {
                        Text(kalima.name, color = IHColors.Gold, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(4.dp))
                        Text(kalima.arabic, color = IHColors.White, fontSize = 20.sp)
                        Spacer(Modifier.height(4.dp))
                        Text(kalima.bangla, color = IHColors.TextSecondary, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}
