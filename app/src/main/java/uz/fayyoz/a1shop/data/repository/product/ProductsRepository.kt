package uz.fayyoz.a1shop.data.repository.product


import kotlinx.coroutines.flow.Flow
import uz.fayyoz.a1shop.model.Products
import uz.fayyoz.a1shop.utill.Resource

interface ProductsRepository {

    fun getByCategory(category: Int): Flow<Resource<List<Products>>>

    fun getAllProducts(): Flow<Resource<List<Products>>>

    suspend fun updateProduct(products: Products)

    suspend fun resetAllFavProducts()

    fun getAllFavProducts(): Flow<List<Products>>

    fun getAllProductsOnCart(): Flow<List<Products>>

}