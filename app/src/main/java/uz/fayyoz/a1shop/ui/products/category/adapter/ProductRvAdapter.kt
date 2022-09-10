package uz.fayyoz.a1shop.ui.products.category.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.fayyoz.a1shop.model.Products
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.ItemProductBinding
import uz.fayyoz.a1shop.utill.getDrawable
import uz.fayyoz.a1shop.utill.inflate
import uz.fayyoz.a1shop.utill.setImageRemote
import uz.fayyoz.a1shop.utill.ProductsComparator

class ProductAdapter(
    private val onItemClick: (Products) -> Unit,
    private val onBookmarkClick: (Products) -> Unit,
) : ListAdapter<Products, ProductVH>(ProductsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        return ProductVH(parent.inflate(R.layout.item_product),
            onItemClick = { position ->
                val product = getItem(position)
                if (product != null) {
                    onItemClick(product)
                }
            },
            onBookmarkClick = { position ->
                val product = getItem(position)
                if (product != null) {
                    onBookmarkClick(product)
                }
            })
    }

    override fun onBindViewHolder(holder: ProductVH, position: Int) {

        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.onBind(currentItem)
        }
    }
}

class ProductVH(
    view: View,
    private val onItemClick: (Int) -> Unit,
    private val onBookmarkClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProductBinding.bind(view)
    private val errImg = getDrawable(view.context, R.drawable.error_image)

    @SuppressLint("SetTextI18n")
    fun onBind(
        product: Products,
    ) {
        binding.apply {
            itemImage.setImageRemote(product.images[0], errImg)
            itemTitle.text = product.title ?: ""
            itemPrice.text = (product.price.toInt().toString() + " $") ?: ""

            imgFav.setImageResource(
                when {
                    product.isFavorite -> R.drawable.ic_fav_product_selected
                    else -> R.drawable.ic_fav_product
                }
            )
        }
    }

    init {
        binding.apply {
            root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(position)
                }
            }
            imgFav.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onBookmarkClick(position)
                }
            }
        }
    }
}
