package uz.fayyoz.a1shop.data.repository.product


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import uz.fayyoz.a1shop.data.local.db.ProductDao
import uz.fayyoz.a1shop.data.local.db.UserDao
import uz.fayyoz.a1shop.data.local.pref.ProductSyncPref
import uz.fayyoz.a1shop.model.Products
import uz.fayyoz.a1shop.network.ShopService
import uz.fayyoz.a1shop.utill.networkBoundResource
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.days

class ProductRepoImpl(
    private val shopService: ShopService,
    private val productDao: ProductDao,
    private val productSyncPref: ProductSyncPref,
    private val userDao: UserDao
) : ProductsRepository{

    companion object {
        @ExperimentalTime
        private val DATA_EXPIRY_IN_MILLIS = 1.days.toDouble(DurationUnit.MILLISECONDS).toLong()
    }

    @OptIn(ExperimentalTime::class)
    override fun getAllProducts() = networkBoundResource(
        query = {
            productDao.getAllProducts()
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
    ).flowOn(Dispatchers.IO)

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
    ).flowOn(Dispatchers.IO)

    @ExperimentalTime
    private fun isExpired(lastSynced: Long): Boolean {
        val currentTime = System.currentTimeMillis()
        return (currentTime - lastSynced) >= DATA_EXPIRY_IN_MILLIS
    }

    override suspend fun updateProduct(product: Products) = withContext(Dispatchers.IO) {
        productDao.updateProduct(product)
    }

    override suspend fun resetAllFavProducts() = withContext(Dispatchers.IO) {
        productDao.resetAllFavorites()
    }

    override fun getAllFavProducts(): Flow<List<Products>> =
        productDao.getAllFavProducts().flowOn(Dispatchers.IO)

    override fun getAllProductsOnCart(): Flow<List<Products>> =
        productDao.getAllProductsOnCart().flowOn(Dispatchers.IO)

}