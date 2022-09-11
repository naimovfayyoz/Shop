package uz.fayyoz.a1shop.ui.products.favorites

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.FavoritesFragmentBinding
import uz.fayyoz.a1shop.ui.BaseFragment
import uz.fayyoz.a1shop.ui.products.category.adapter.ProductAdapter
import uz.fayyoz.a1shop.ui.products.favorites.vm.FavoritesVM
import uz.fayyoz.a1shop.utill.ViewModelFactory

class FavoritesFragment : BaseFragment<FavoritesFragmentBinding>(R.layout.category_fragment) {

    private val favoritesVM by viewModels<FavoritesVM> { ViewModelFactory() }

    override fun initViewBinding(view: View): FavoritesFragmentBinding =
        FavoritesFragmentBinding.bind(view)

    private val favoritesAdapter = ProductAdapter(
        onItemClick = { product ->

        },
        onBookmarkClick = { article ->
            favoritesVM.onFavoriteClick(article)
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            favoritesVM.favProducts.collect { favoritesAdapter.submitList(it)
                println(it)
            }
        }
        setUpRv()
    }


    private fun setUpRv() {
        binding.recyclerView.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
            adapter = favoritesAdapter
        }
    }
}