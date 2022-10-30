package uz.fayyoz.a1shop.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import uz.fayyoz.a1shop.model.Category
import uz.fayyoz.a1shop.model.Products
import uz.fayyoz.a1shop.model.User
import uz.fayyoz.a1shop.utill.ListTypeConverter


@Database(entities = [User::class, Products::class, Category::class], version = 1)
@TypeConverters(ListTypeConverter::class)
@kotlinx.serialization.ExperimentalSerializationApi
abstract class ShopDB : RoomDatabase() {

    abstract fun productsDao(): ProductDao

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: ShopDB? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabaseInstance(context: Context): ShopDB {
            synchronized(this) {
                return instance ?: Room.databaseBuilder(
                    context,
                    ShopDB::class.java, "1shop_db"
                )
                    .fallbackToDestructiveMigration()
                    .build().also {
                        instance = it
                    }
            }
        }
    }
}