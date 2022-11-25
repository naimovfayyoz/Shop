package uz.fayyoz.a1shop.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "products")
@Parcelize
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
    val isOnCart: Boolean = false,
) : Parcelable