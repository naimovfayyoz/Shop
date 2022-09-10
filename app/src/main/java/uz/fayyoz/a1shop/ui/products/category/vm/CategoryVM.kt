package uz.fayyoz.a1shop.ui.products.category.vm

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import uz.fayyoz.a1shop.data.repository.product.ProductsRepository
import uz.fayyoz.a1shop.model.Products
import uz.fayyoz.a1shop.ui.BaseViewModel

class CategoryVM(private val repository: ProductsRepository) : BaseViewModel() {

   // val products = viewModelScope.launch { repository.getAllProducts().asLiveData() }

    //    private val _products =
//        MutableStateFlow<Resource<PagingData<Products>>>(Resource.InitialState())
//    val products = _products.asStateFlow()

    fun onFavoriteClick(product: Products) {
        val currentlyBookmarked = product.isFavorite
        val updatedArticle = product.copy(isFavorite = !currentlyBookmarked)
        viewModelScope.launch {
            repository.updateProduct(updatedArticle)
        }
    }

    fun getByCategory(id: Int) = repository.getByCategory(id).stateIn(viewModelScope,
        SharingStarted.Lazily, null)

}