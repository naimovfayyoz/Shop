package uz.fayyoz.a1shop.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.fayyoz.a1shop.model.Products
import uz.fayyoz.a1shop.model.User

@Dao
interface UserDao {

    @Query("Select * from user")
    fun getUser(): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData(user: User)

    @Query("Delete from user")
    suspend fun deleteUser()

}