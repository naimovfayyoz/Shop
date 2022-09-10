package uz.fayyoz.a1shop.data.repository.product

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import uz.fayyoz.a1shop.model.Products
import uz.fayyoz.a1shop.utill.Resource

interface ProductsRepository {

    // suspend fun getProducts()

    fun getByCategory(category: Int): Flow<Resource<List<Products>>>

    // fun getAllProducts(): Flow<Resource<List<Products>>>

    suspend fun updateProduct(products: Products)

    suspend fun resetAllFavProducts()

    fun getAllFavProducts(): Flow<List<Products>>

}