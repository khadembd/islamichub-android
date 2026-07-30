package com.islamichub.app.ui.dua

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.islamichub.app.model.DuaData
import com.islamichub.app.ui.theme.IHColors

@Composable
fun DuaScreen(repo: DataRepository) {
    var duaData by remember { mutableStateOf<DuaData?>(null) }
    var selectedCategory by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        duaData = repo.loadDua()
        if (duaData?.categories?.isNotEmpty() == true) {
            selectedCategory = duaData!!.categories.first().id
        }
    }

    Column(Modifier.fillMaxSize().background(IHColors.BG)) {
        Text("দোয়া সংকলন", color = IHColors.White, fontSize = 20.sp, fontWeight = FontWeight.Black,
            modifier = Modifier.padding(16.dp))
        // Category chips
        duaData?.categories?.let { cats ->
            LazyRow(contentPadding = PaddingValues(horizontal = 12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(cats) { cat ->
                    Box(
                        Modifier.clip(RoundedCornerShape(16.dp))
                            .background(if (selectedCategory == cat.id) IHColors.Primary else IHColors.Surface)
                            .clickable { selectedCategory = cat.id }
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Text("${cat.icon} ${cat.name}", color = IHColors.White, fontSize = 12.sp,
                            fontWeight = if (selectedCategory == cat.id) FontWeight.Bold else FontWeight.Medium)
                    }
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        // Dua list
        duaData?.duas?.get(selectedCategory)?.let { duas ->
            LazyColumn(contentPadding = PaddingValues(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(duas) { dua ->
                    Box(
                        Modifier.clip(RoundedCornerShape(12.dp)).background(IHColors.Surface).padding(16.dp)
                    ) {
                        Column {
                            Text(dua.arabic, color = IHColors.White, fontSize = 20.sp)
                            Spacer(Modifier.height(4.dp))
                            dua.transliteration?.let { Text(it, color = IHColors.Accent, fontSize = 12.sp) }
                            Text(dua.bangla, color = IHColors.TextSecondary, fontSize = 13.sp)
                            dua.reference?.let { Text("— $it", color = IHColors.Gold, fontSize = 10.sp) }
                        }
                    }
                }
            }
        }
    }
}
