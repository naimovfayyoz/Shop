//package uz.fayyoz.a1shop.ui.products.allProducts
//
//import android.os.Bundle
//import android.view.View
//import androidx.lifecycle.lifecycleScope
//import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.LinearLayoutManager
//import uz.fayyoz.a1shop.R
//import uz.fayyoz.a1shop.databinding.AllProductsFragmentBinding
//import uz.fayyoz.a1shop.databinding.FavoritesFragmentBinding
//import uz.fayyoz.a1shop.ui.BaseFragment
//import uz.fayyoz.a1shop.ui.products.category.adapter.ProductAdapter
//
//class AllProductsFragment :
//    BaseFragment<AllProductsFragmentBinding>(R.layout.all_products_fragment) {
//    override fun initViewBinding(view: View): AllProductsFragmentBinding =
//        AllProductsFragmentBinding.bind(view)
//
//    private val productsAdapter: ProductAdapter = ProductAdapter(onItemClick = { product ->
//
//    },
//        onBookmarkClick = { article ->
//            productVM.onFavoriteClick(article)
//        })
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            favoritesVM.favProducts.collect { favoritesAdapter.submitList(it)
//                println(it)
//            }
//        }
//        setUpRv()
//    }
//    private fun setUpRv() {
//        binding.recyclerView.apply {
//            layoutManager =
//                GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
//            adapter = favoritesAdapter
//        }
//    }
//}