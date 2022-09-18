package uz.fayyoz.a1shop.ui.products.allProducts

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.AllProductsFragmentBinding
import uz.fayyoz.a1shop.model.Products
import uz.fayyoz.a1shop.ui.BaseFragment
import uz.fayyoz.a1shop.ui.main.MainFragmentDirections
import uz.fayyoz.a1shop.ui.products.allProducts.vm.AllProductsVM
import uz.fayyoz.a1shop.ui.products.adapter.ProductAdapter
import uz.fayyoz.a1shop.ui.products.listener.OnProductClickListener
import uz.fayyoz.a1shop.utill.Resource
import uz.fayyoz.a1shop.utill.ViewModelFactory
import uz.fayyoz.a1shop.utill.navigate

class AllProductsFragment :
    BaseFragment<AllProductsFragmentBinding>(R.layout.all_products_fragment) {

    private val allProductsVM by viewModels<AllProductsVM> { ViewModelFactory() }


    override fun initViewBinding(view: View): AllProductsFragmentBinding =
        AllProductsFragmentBinding.bind(view)

    private val productsAdapter: ProductAdapter = ProductAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()
        setUpListeners()
        setUpRv()
    }

    private fun setUpListeners() {
        productsAdapter.onProductClickListener(object : OnProductClickListener {
            override fun onFavoriteClick(product: Products) {
                allProductsVM.onFavoriteClick(product)
            }

            override fun onProductClick(product: Products) {
                val action =
                    AllProductsFragmentDirections.actionAllProductsFragmentToProductsDetailsFragment(
                        product)
                navigate(R.id.allProductsFragment, action)
            }

        })


    }

    override fun subscribeObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            allProductsVM.allProducts.collect {
                val result = it ?: return@collect
                binding.apply {
                    swipeRefreshLayout.isRefreshing = result is Resource.Loading
                    progressBar.isVisible =
                        result is Resource.Loading || result.data.isNullOrEmpty()

                    recyclerView.isVisible = !result.data.isNullOrEmpty()
                    textViewError.isVisible = result.error != null && result.data.isNullOrEmpty()
                    buttonRetry.isVisible = result.error != null && result.data.isNullOrEmpty()
                    textViewError.text = getString(
                        R.string.could_not_refresh,
                        result.error?.localizedMessage
                            ?: getString(R.string.unknown_error_occurred))
                }
                productsAdapter.submitList(it.data)
            }
        }
    }

    private fun setUpRv() {
        binding.recyclerView.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
            adapter = productsAdapter
        }
    }
}