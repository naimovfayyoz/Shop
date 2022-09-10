package uz.fayyoz.a1shop.di

import androidx.room.Room
import kotlinx.serialization.ExperimentalSerializationApi
import uz.fayyoz.a1shop.data.local.db.ProductsDB
import uz.fayyoz.a1shop.data.local.db.ProductDao
import uz.fayyoz.a1shop.utill.App

object  DatabaseModule {

    private val database = provideDatabase()

    @OptIn(ExperimentalSerializationApi::class)
    fun provideDatabase(): ProductsDB {
        return Room.databaseBuilder(App.appInstance, ProductsDB::class.java, "uz.fayyoz.1shop_db")
            .build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun provideProductsDao(): ProductDao {
        return database.productsDao()
    }


}