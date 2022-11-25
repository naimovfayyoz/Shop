package uz.fayyoz.a1shop.ui.products.adapter

import android.animation.Animator
import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.fayyoz.a1shop.model.Products
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.ItemProductBinding
import uz.fayyoz.a1shop.ui.products.listener.OnProductClickListener
import uz.fayyoz.a1shop.utill.getDrawable
import uz.fayyoz.a1shop.utill.inflate
import uz.fayyoz.a1shop.utill.setImageRemote
import uz.fayyoz.a1shop.utill.ProductsComparator

class ProductAdapter() : ListAdapter<Products, ProductAdapter.ProductVH>(ProductsComparator()) {

    var productClickListener: OnProductClickListener? = null
    fun onProductClickListener(listener: OnProductClickListener) {
        productClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        return ProductVH(parent.inflate(R.layout.item_product))
    }

    override fun onBindViewHolder(holder: ProductVH, position: Int) {

        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.onBind(currentItem)
        }
    }

    inner class ProductVH(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemProductBinding.bind(view)
        private val errImg = getDrawable(view.context, R.drawable.error_image)

        @SuppressLint("SetTextI18n")
        fun onBind(
            product: Products,
        ) {
            binding.apply {
                itemImage.setImageRemote(product.images[0], errImg)
                itemImage.transitionName = "image"
                itemTitle.text = product.title ?: ""
                itemPrice.text = (product.price.toInt().toString() + " $") ?: ""

                imgFav.setImageResource(
                    when {
                        product.isFavorite -> R.drawable.ic_fav_product_selected
                        else -> R.drawable.ic_fav_product
                    }
                )

                root.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        productClickListener?.onProductClick(product, itemImage)

                    }
                }
                imgFav.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        imgFav.animate().apply {
                            setListener(object : Animator.AnimatorListener {
                                override fun onAnimationStart(animation: Animator) {
                                    scaleX(2f).scaleY(2f).duration = 1000

                                }

                                override fun onAnimationEnd(animation: Animator) {
                                    imgFav.animate().scaleX(1f).scaleY(1f)
                                }

                                override fun onAnimationCancel(animation: Animator) {
                                }

                                override fun onAnimationRepeat(animation: Animator) {
                                }

                            })
                        }
                        productClickListener?.onFavoriteClick(product)

                    }
                }
            }
        }
    }
}