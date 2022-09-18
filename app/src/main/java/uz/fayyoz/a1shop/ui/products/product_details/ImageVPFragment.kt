package uz.fayyoz.a1shop.ui.products.product_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.ProductImagePagerBinding
import uz.fayyoz.a1shop.ui.BaseFragment
import uz.fayyoz.a1shop.ui.products.product_details.adapter.ProductsDetailsAdapter


class ImageVPFragment : BaseFragment<ProductImagePagerBinding>(R.layout.product_image_pager) {

    override fun initViewBinding(view: View) = ProductImagePagerBinding.bind(view)

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabLayout()

    }

    private fun setUpTabLayout() {
        binding.apply {
            val title = arrayOf("Clothes", "Electronics", "Furniture", "Shoes", "Others")
            vpCategories.adapter = ProductsDetailsAdapter(this@ImageVPFragment)
            TabLayoutMediator(tabLayout, vpCategories) { tab, position ->
                tab.text = title[position]
            }.attach()
        }
    }
}