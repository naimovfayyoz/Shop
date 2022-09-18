package uz.fayyoz.a1shop.ui.products.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.FavoritesFragmentBinding
import uz.fayyoz.a1shop.model.Products
import uz.fayyoz.a1shop.ui.BaseFragment
import uz.fayyoz.a1shop.ui.products.adapter.ProductAdapter
import uz.fayyoz.a1shop.ui.products.allProducts.AllProductsFragmentDirections
import uz.fayyoz.a1shop.ui.products.favorites.vm.FavoritesVM
import uz.fayyoz.a1shop.ui.products.listener.OnProductClickListener
import uz.fayyoz.a1shop.utill.ViewModelFactory
import uz.fayyoz.a1shop.utill.navigate

class FavoritesFragment : BaseFragment<FavoritesFragmentBinding>(R.layout.category_fragment) {

    private val favoritesVM by viewModels<FavoritesVM> { ViewModelFactory() }

    override fun initViewBinding(view: View): FavoritesFragmentBinding =
        FavoritesFragmentBinding.bind(view)

    private val favoritesAdapter = ProductAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()
        subscribeObservers()
        setUpRv()
    }

    override fun subscribeObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            favoritesVM.favProducts.collect {
                favoritesAdapter.submitList(it)
            }
        }
    }

    private fun setUpRv() {
        binding.recyclerView.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
            adapter = favoritesAdapter
        }
    }

    private fun setUpListeners() {
        favoritesAdapter.onProductClickListener(object : OnProductClickListener {

            override fun onFavoriteClick(product: Products) {
                favoritesVM.onFavoriteClick(product)
            }

            override fun onProductClick(product: Products) {
                val action =
                    FavoritesFragmentDirections.actionFavoritesFragmentToProductsDetailsFragment(
                        product)
                navigate(R.id.favoritesFragment, action)
            }

        })
    }
}