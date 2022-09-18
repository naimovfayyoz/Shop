package uz.fayyoz.a1shop.ui.products.product_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.ProductDetailsFragmentBinding
import uz.fayyoz.a1shop.ui.BaseFragment

import uz.fayyoz.a1shop.utill.getDrawable
import uz.fayyoz.a1shop.utill.setImageRemote

class ProductsDetailsFragment :
    BaseFragment<ProductDetailsFragmentBinding>(R.layout.product_details_fragment) {
    private val args by navArgs<ProductsDetailsFragmentArgs>()

    override fun initViewBinding(view: View): ProductDetailsFragmentBinding =
        ProductDetailsFragmentBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt(POSITION)
        setUpViews(position!!)


    }


    @SuppressLint("SetTextI18n")
    private fun setUpViews(position: Int) {
        println(position)
        binding.apply {
            args.product.apply {
                productImageView.setImageRemote(images[position],
                    getDrawable(requireContext(), R.drawable.error_image))
                productTitle.text = title
                productPrice.text = "$$price"
                productDescription.text = description
            }

        }
    }

    companion object {
        var POSITION = "image_arg"
        @JvmStatic
        fun newImage(position: Int) = ProductsDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(POSITION, position + 1)
            }
        }
    }
}
