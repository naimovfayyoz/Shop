package uz.fayyoz.a1shop.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.tabs.TabLayoutMediator
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.CategoryFragmentBinding
import uz.fayyoz.a1shop.databinding.FavoritesFragmentBinding
import uz.fayyoz.a1shop.databinding.MainFragmentBinding
import uz.fayyoz.a1shop.ui.BaseFragment
import uz.fayyoz.a1shop.ui.main.adapter.PagerAdapter
import uz.fayyoz.a1shop.ui.products.category.CategoryFragment
import uz.fayyoz.a1shop.ui.products.favorites.FavoritesFragment
import uz.fayyoz.a1shop.ui.products.favorites.vm.FavoritesVM
import uz.fayyoz.a1shop.utill.navigate
import java.lang.IllegalArgumentException

class MainFragment : BaseFragment<MainFragmentBinding>(R.layout.main_fragment) {

    override fun initViewBinding(view: View) = MainFragmentBinding.bind(view)

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabLayout()

        //setWithNav...


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