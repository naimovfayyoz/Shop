package uz.fayyoz.a1shop.utill

import androidx.recyclerview.widget.DiffUtil
import uz.fayyoz.a1shop.model.Products

class ProductsComparator : DiffUtil.ItemCallback<Products>() {
    override fun areItemsTheSame(oldItem: Products, newItem: Products) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Products, newItem: Products) = oldItem == newItem

}
