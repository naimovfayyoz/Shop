package uz.fayyoz.a1shop.ui.products.allProducts.vm

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import uz.fayyoz.a1shop.data.repository.product.ProductsRepository
import uz.fayyoz.a1shop.model.Products
import uz.fayyoz.a1shop.ui.BaseViewModel

class AllProductsVM(private val repository: ProductsRepository) : BaseViewModel() {


    val allProducts =   repository.getAllProducts()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun onFavoriteClick(product: Products) {
        val currentlyBookmarked = product.isFavorite
        val updatedArticle = product.copy(isFavorite = !currentlyBookmarked)
        viewModelScope.launch {
            repository.updateProduct(updatedArticle)
        }
    }

}