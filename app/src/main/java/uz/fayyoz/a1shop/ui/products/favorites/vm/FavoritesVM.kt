package uz.fayyoz.a1shop.ui.products.favorites.vm

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import uz.fayyoz.a1shop.data.repository.product.ProductsRepository
import uz.fayyoz.a1shop.model.Products
import uz.fayyoz.a1shop.ui.BaseViewModel

class FavoritesVM(private val repository: ProductsRepository) : BaseViewModel() {


    val favProducts = repository.getAllFavProducts()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun onFavoriteClick(product: Products) {
        val currentlyFavorite = product.isFavorite
        val updatedFav = product.copy(isFavorite = !currentlyFavorite)
        viewModelScope.launch {
            repository.updateProduct(updatedFav)
        }
    }

    fun onDeleteAllFavorites() {
        viewModelScope.launch {
            repository.resetAllFavProducts()
        }
    }
}