package uz.fayyoz.a1shop.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "category")
@Parcelize
data class Category(
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String
) : Parcelable