package com.example.newsapp.data.local.db

import androidx.room.TypeConverter
import com.example.newsapp.data.models.api.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name,name)

    }
}