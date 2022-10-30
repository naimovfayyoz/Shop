package uz.fayyoz.a1shop.ui.products.product_details.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.ItemImageBinding
import uz.fayyoz.a1shop.utill.ImageComparator
import uz.fayyoz.a1shop.utill.getDrawable
import uz.fayyoz.a1shop.utill.inflate
import uz.fayyoz.a1shop.utill.setImageRemote

class ProductDetailsAdapter : ListAdapter<String, ProductDetailsAdapter.ProductDetailsVH>(
    ImageComparator()) {


    class ProductDetailsVH(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemImageBinding.bind(view)
        private val errImg = getDrawable(view.context, R.drawable.error_image)

        fun onBind(images: String) {
            binding.imageView.setImageRemote(images, errImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailsVH {
        return ProductDetailsVH(parent.inflate(R.layout.item_image))
    }

    override fun onBindViewHolder(holder: ProductDetailsVH, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.onBind(currentItem)
        }
    }
}