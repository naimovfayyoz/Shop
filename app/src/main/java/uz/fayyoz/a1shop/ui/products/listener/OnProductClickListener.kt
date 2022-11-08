package uz.fayyoz.a1shop.ui.products.listener

import android.widget.ImageView
import uz.fayyoz.a1shop.model.Products

interface OnProductClickListener {

    fun onFavoriteClick(product: Products)

    fun onProductClick(product: Products,imageView: ImageView)
}