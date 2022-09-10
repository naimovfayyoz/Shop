package uz.fayyoz.a1shop.data.repository.product


import androidx.lifecycle.asLiveData
import androidx.room.util.query
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import uz.fayyoz.a1shop.data.local.db.ProductDao
import uz.fayyoz.a1shop.data.local.pref.ProductSyncPref
import uz.fayyoz.a1shop.model.Products
import uz.fayyoz.a1shop.network.ShopService
import uz.fayyoz.a1shop.utill.BaseNetworkRepo
import uz.fayyoz.a1shop.utill.isNull
import uz.fayyoz.a1shop.utill.networkBoundResource
import kotlin.time.ExperimentalTime
import kotlin.time.days

class ProductRepoImpl(
    private val shopService: ShopService,
    private val productDao: ProductDao,
    private val productSyncPref: ProductSyncPref,
) : ProductsRepository,
    BaseNetworkRepo() {

    companion object {
        @ExperimentalTime
        private val DATA_EXPIRY_IN_MILLIS = 1.days.inMilliseconds.toLong()
    }

//    override  fun getAllProducts() = networkBoundResource(
//        query = {
//            productDao.getAllProducts()
//        },
//        fetch = {
//            shopService.getAllProducts()
//        },
//        saveFetchResult = { products ->
//            products.forEach { product ->
//                if (product.image.isNullOrBlank()) {
//                    product.image = ""
//                }
//            }
//            productDao.deleteAllProducts()
//            productDao.insertProducts(products)
//        }
//
//    )

    override suspend fun updateProduct(product: Products) {
        productDao.updateProduct(product)
    }

    override suspend fun resetAllFavProducts() {
        productDao.resetAllFavorites()
    }

    override fun getAllFavProducts(): Flow<List<Products>> = productDao.getAllFavProducts()


    @OptIn(ExperimentalTime::class)
    override fun getByCategory(category: Int) = networkBoundResource(
        query = {
            productDao.getProductsByCategory(category)
        },
        fetch = {
            shopService.getAllProducts()
        },
        saveFetchResult = { products ->
            products.forEach { product ->
                if (product.image.isNullOrBlank()) {
                    product.image = ""
                }
            }
            productSyncPref.saveLastSyncedTime(System.currentTimeMillis())
            productDao.deleteAllProducts()
            productDao.insertProducts(products)
        },
        shouldFetch = {
            val lastSynced = productSyncPref.lastSyncedTime.first()
             lastSynced == -1L || it.isNullOrEmpty() || isExpired(lastSynced)
        }
    )
    @ExperimentalTime
    private fun isExpired(lastSynced: Long): Boolean {
        val currentTime = System.currentTimeMillis()
        return (currentTime - lastSynced) >= DATA_EXPIRY_IN_MILLIS
    }

//    override fun createUser() {
//        coroutineScope.launch {
//            shopService.login(
//                "john@mail.com",
//                "changeme",
//            )
//        }
//    }

}