package uz.fayyoz.a1shop.ui.products.product_details.vm

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import uz.fayyoz.a1shop.data.repository.product.ProductsRepository
import uz.fayyoz.a1shop.model.Products
import uz.fayyoz.a1shop.ui.BaseViewModel

class ProductsDetailsVM(private val repository: ProductsRepository) : BaseViewModel() {

    val onCartProducts = repository.getAllProductsOnCart()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun addOnCartProduct(product: Products) {
        val currentlyOnCart = product.isOnCart
        val updatedCart = product.copy(isOnCart = !currentlyOnCart)
        viewModelScope.launch {
            repository.updateProduct(updatedCart)
        }
    }

}