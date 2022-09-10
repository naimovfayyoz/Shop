package uz.fayyoz.a1shop.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

@Entity(tableName = "user")
data class User(
    @SerializedName("id")
    @Expose
    @PrimaryKey
    val id: Int,
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("password")
    @Expose
    val password: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("role")
    @Expose
    val role: String,
    @SerializedName("avatar")
    @Expose
    val avatar: String,
)
