package com.example.newsapp.data.models

import androidx.room.ColumnInfo
import kotlinx.serialization.Serializable

@Serializable
data class Source(
    @ColumnInfo(name = "source_id")val id: String,
    val name: String
)