package uz.fayyoz.a1shop.ui.products.category

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.CategoryFragmentBinding
import uz.fayyoz.a1shop.ui.BaseFragment
import uz.fayyoz.a1shop.ui.products.category.adapter.ProductAdapter
import uz.fayyoz.a1shop.ui.products.category.vm.CategoryVM
import uz.fayyoz.a1shop.utill.Resource
import uz.fayyoz.a1shop.utill.ViewModelFactory

class CategoryFragment() : BaseFragment<CategoryFragmentBinding>(R.layout.category_fragment) {

    override fun initViewBinding(view: View): CategoryFragmentBinding =
        CategoryFragmentBinding.bind(view)

    private val productVM by viewModels<CategoryVM> { ViewModelFactory() }
    private val productsAdapter: ProductAdapter = ProductAdapter(onItemClick = { product ->

    },
        onBookmarkClick = { article ->
            productVM.onFavoriteClick(article)
        })


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt(POSITION_ARG)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

            productVM.getByCategory(position!!).collect {
                val result = it ?: return@collect
                binding.apply {
                    swipeRefreshLayout.isRefreshing = result is Resource.Loading
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
        setUpRv()
    }

    private fun setUpRv() {
        binding.recyclerView.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
            adapter = productsAdapter
        }
    }

    companion object {

        var POSITION_ARG = "position_arg"

        @JvmStatic
        fun newInstance(position: Int) = CategoryFragment().apply {
            arguments = Bundle().apply {
                putInt(POSITION_ARG, position + 1)
            }
        }
    }
}