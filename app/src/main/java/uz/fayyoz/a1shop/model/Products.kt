package uz.fayyoz.a1shop.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Products(
    val description: String,
    @PrimaryKey
    val id: Int,
    var image: String,
    @ColumnInfo("category")
    var category: Category,
    val images: List<String>,
    val price: Double,
    val title: String,
    val isFavorite: Boolean = false,
)