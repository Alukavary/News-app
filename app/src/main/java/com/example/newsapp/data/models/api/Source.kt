package com.example.newsapp.data.models.api

import androidx.room.ColumnInfo
import kotlinx.serialization.Serializable

@Serializable
class Source(
    @ColumnInfo(name = "source_id")val id: String,
    val name: String
)