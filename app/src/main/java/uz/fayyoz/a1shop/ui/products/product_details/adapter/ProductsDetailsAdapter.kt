package uz.fayyoz.a1shop.ui.products.product_details.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.fayyoz.a1shop.ui.products.product_details.ProductsDetailsFragment

class ProductsDetailsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3


    override fun createFragment(position: Int): Fragment {
        return ProductsDetailsFragment.newImage(position)
    }
}