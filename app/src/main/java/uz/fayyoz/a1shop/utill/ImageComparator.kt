package uz.fayyoz.a1shop.utill

import androidx.recyclerview.widget.DiffUtil
import uz.fayyoz.a1shop.model.Products

class ImageComparator : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem


    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem.equals(newItem)


}