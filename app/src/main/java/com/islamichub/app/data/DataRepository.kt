package com.islamichub.app.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import com.islamichub.app.model.*
import timber.log.Timber

class DataRepository(private val context: Context) {

    private val json = Json { ignoreUnknownKeys = true; isLenient = true }

    private fun readAsset(name: String): String =
        context.assets.open("data/$name").bufferedReader().use { it.readText() }

    suspend fun loadAsmaulHusna(): List<AsmaulHusna> = withContext(Dispatchers.IO) {
        try {
            json.decodeFromString(readAsset("asmaul_husna.json"))
        } catch (e: Exception) { Timber.e(e); emptyList() }
    }

    suspend fun loadKalima(): List<Kalima> = withContext(Dispatchers.IO) {
        try {
            json.decodeFromString<KalimaData>(readAsset("kalima.json")).kalimas
        } catch (e: Exception) { Timber.e(e); emptyList() }
    }

    suspend fun loadHadith(): List<Hadith> = withContext(Dispatchers.IO) {
        try {
            json.decodeFromString<HadithData>(readAsset("hadith.json")).hadiths
        } catch (e: Exception) { Timber.e(e); emptyList() }
    }

    suspend fun loadDua(): DuaData = withContext(Dispatchers.IO) {
        try {
            json.decodeFromString(readAsset("dua.json"))
        } catch (e: Exception) { Timber.e(e); DuaData(emptyList()) }
    }

    suspend fun loadQuestions(): QuestionData = withContext(Dispatchers.IO) {
        try {
            json.decodeFromString(readAsset("questions.json"))
        } catch (e: Exception) { Timber.e(e); QuestionData() }
    }

    suspend fun loadNamaz(): String = withContext(Dispatchers.IO) {
        try { readAsset("namaz.json") } catch (e: Exception) { "{}" }
    }

    suspend fun loadIslamicStories(): String = withContext(Dispatchers.IO) {
        try { readAsset("islamic_stories.json") } catch (e: Exception) { "{}" }
    }

    suspend fun loadExtendedHadith(): String = withContext(Dispatchers.IO) {
        try { readAsset("extended_hadith.json") } catch (e: Exception) { "{}" }
    }
}
