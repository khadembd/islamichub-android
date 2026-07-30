package com.islamichub.app.ui.asmaul

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.islamichub.app.data.DataRepository
import com.islamichub.app.model.AsmaulHusna
import com.islamichub.app.ui.theme.IHColors

@Composable
fun AsmalHusnaScreen(repo: DataRepository) {
    var items by remember { mutableStateOf<List<AsmaulHusna>>(emptyList()) }
    LaunchedEffect(Unit) { items = repo.loadAsmaulHusna() }

    Column(Modifier.fillMaxSize().background(IHColors.BG)) {
        Text("আসমাউল হুসনা", color = IHColors.White, fontSize = 20.sp, fontWeight = FontWeight.Black,
            modifier = Modifier.padding(16.dp))
        Text("৯৯টি সুন্দর নাম", color = IHColors.TextSecondary, fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(8.dp))
        LazyColumn(contentPadding = PaddingValues(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items) { name ->
                AsmaulCard(name)
            }
        }
    }
}

@Composable
private fun AsmaulCard(name: AsmaulHusna) {
    Box(
        Modifier.clip(RoundedCornerShape(12.dp))
            .background(IHColors.Surface)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("${name.id}", color = IHColors.Gold, fontSize = 12.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.size(28.dp).clip(RoundedCornerShape(6.dp))
                    .background(IHColors.PrimaryDark).wrapContentSize())
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(name.arabic, color = IHColors.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(name.transliteration, color = IHColors.Accent, fontSize = 13.sp)
                Text(name.meaning, color = IHColors.TextSecondary, fontSize = 11.sp)
            }
        }
    }
}
