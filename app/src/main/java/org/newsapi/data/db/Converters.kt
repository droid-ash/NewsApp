package org.newsapi.data.db

import androidx.room.TypeConverter
import com.newsapi.api.model.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String = source.name

    @TypeConverter
    fun toSource(name: String): Source = Source(id = null, name = name)
}