package uz.fayyoz.a1shop.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String
)