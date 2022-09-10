package uz.fayyoz.a1shop.utill

import android.media.Image
import androidx.room.TypeConverter
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import uz.fayyoz.a1shop.model.Category

class ListTypeConverter {

    @TypeConverter
    fun fromList(value: List<String>): String = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<String>>(value)

    @TypeConverter
    fun fromCategory(category: Category) = category.id

    @TypeConverter
    fun toCategory(id: Int): Category = Category(id,"","")

}