package com.islamichub.app.model

import kotlinx.serialization.Serializable

@Serializable
data class AsmaulHusna(
    val id: Int,
    val arabic: String,
    val transliteration: String,
    val meaning: String,
    val explanation: String
)

@Serializable
data class Kalima(
    val id: Int,
    val name: String,
    val arabic: String,
    val bangla: String,
    val transliteration: String? = null,
    val explanation: String? = null
)

@Serializable
data class KalimaData(val kalimas: List<Kalima>)

@Serializable
data class Hadith(
    val id: Int,
    val title: String,
    val arabic: String,
    val bangla: String,
    val reference: String,
    val explanation: String
)

@Serializable
data class HadithData(val hadiths: List<Hadith>)

@Serializable
data class DuaCategory(
    val id: String,
    val name: String,
    val icon: String,
    val color: String
)

@Serializable
data class DuaItem(
    val arabic: String,
    val transliteration: String? = null,
    val bangla: String,
    val reference: String? = null,
    val note: String? = null
)

@Serializable
data class DuaData(
    val categories: List<DuaCategory>,
    val duas: Map<String, List<DuaItem>> = emptyMap()
)

@Serializable
data class ZikrItem(
    val name: String,
    val arabic: String,
    val transliteration: String,
    val meaning: String,
    val target: Int,
    val color: String
)

@Serializable
data class QuestionCategory(
    val name: String,
    val icon: String,
    val color: String,
    val questions: List<String>
)

@Serializable
data class QuestionData(
    val categories: Map<String, QuestionCategory> = emptyMap()
)
