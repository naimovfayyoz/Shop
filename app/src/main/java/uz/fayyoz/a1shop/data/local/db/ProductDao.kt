package uz.fayyoz.a1shop.data.local.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.fayyoz.a1shop.model.Products

@Dao
interface ProductDao {


    @Query("SELECT * FROM products WHERE isFavorite == 1")
    fun getAllFavProducts(): Flow<List<Products>>

    @Query("SELECT * FROM products WHERE category == :categoryID")
    fun getProductsByCategory(categoryID: Int): Flow<List<Products>>

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Products>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<Products>)

    @Query("UPDATE products SET isFavorite = 0")
    suspend fun resetAllFavorites()

    // TODO IMPLEMENT IT
    @Update
    suspend fun updateProduct(products: Products)

    @Query("Delete from products")
    suspend fun deleteAllProducts()

}


//    @Transaction
//    @Query("SELECT * FROM  products WHERE id =:categoryID")
//    suspend fun getCategoryWithProducts(categoryID: Int): List<CategoryWithProducts>