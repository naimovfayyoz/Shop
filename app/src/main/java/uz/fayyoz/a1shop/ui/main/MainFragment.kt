package uz.fayyoz.a1shop.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.MainFragmentBinding
import uz.fayyoz.a1shop.ui.BaseFragment
import uz.fayyoz.a1shop.ui.main.adapter.PagerAdapter


class MainFragment : BaseFragment<MainFragmentBinding>(R.layout.main_fragment) {

    override fun initViewBinding(view: View) = MainFragmentBinding.bind(view)

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabLayout()

        //setWithNav...
//        binding.vpImages.adapter = ProductsDetailsAdapter(this)

    }


    private fun setUpTabLayout() {
        val title = arrayOf("Clothes", "Electronics", "Furniture", "Shoes", "Others")
        binding.apply {
            vpCategories.adapter = PagerAdapter(this@MainFragment)
            TabLayoutMediator(tabLayout, vpCategories) { tab, position ->
                tab.text = title[position]
            }.attach()
        }
    }
}