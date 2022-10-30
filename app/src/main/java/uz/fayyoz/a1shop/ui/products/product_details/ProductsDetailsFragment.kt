package uz.fayyoz.a1shop.ui.products.product_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.ProductDetailsFragmentBinding
import uz.fayyoz.a1shop.ui.BaseFragment
import uz.fayyoz.a1shop.ui.products.product_details.adapter.ProductDetailsAdapter

class ProductsDetailsFragment :
    BaseFragment<ProductDetailsFragmentBinding>(R.layout.product_details_fragment) {
    private val args by navArgs<ProductsDetailsFragmentArgs>()

    private val imagesAdapter: ProductDetailsAdapter = ProductDetailsAdapter()


    override fun initViewBinding(view: View): ProductDetailsFragmentBinding =
        ProductDetailsFragmentBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRv()
        setUpViews()
        imagesAdapter.submitList(args.product.images)
    }


    @SuppressLint("SetTextI18n")
    private fun setUpViews() {
        binding.apply {
            args.product.apply {
                productTitle.text = title
                productPrice.text = "$$price"
                productDescription.text = description
            }

        }
    }

    private fun setUpRv() {
        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = imagesAdapter
        }
    }

}
