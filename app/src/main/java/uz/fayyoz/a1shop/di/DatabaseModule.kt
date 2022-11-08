package uz.fayyoz.a1shop.di

import androidx.room.Room
import kotlinx.serialization.ExperimentalSerializationApi
import uz.fayyoz.a1shop.data.local.db.ShopDB
import uz.fayyoz.a1shop.data.local.db.ProductDao
import uz.fayyoz.a1shop.data.local.db.UserDao
import uz.fayyoz.a1shop.App

object  DatabaseModule {

    private val database = provideDatabase()

    @OptIn(ExperimentalSerializationApi::class)
    fun provideDatabase(): ShopDB {
        return Room.databaseBuilder(App.appInstance, ShopDB::class.java, "uz.fayyoz.1shop_db")
            .build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun provideProductsDao(): ProductDao {
        return database.productsDao()
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun provideUserDao(): UserDao {
        return database.userDao()
    }

}