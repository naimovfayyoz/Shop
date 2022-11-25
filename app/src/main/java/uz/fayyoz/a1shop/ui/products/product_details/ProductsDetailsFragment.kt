package uz.fayyoz.a1shop.ui.products.product_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.ProductDetailsBinding
import uz.fayyoz.a1shop.ui.BaseFragment
import uz.fayyoz.a1shop.ui.products.product_details.adapter.ImageSliderAdapter
import uz.fayyoz.a1shop.ui.products.product_details.vm.ProductsDetailsVM
import uz.fayyoz.a1shop.utill.ViewModelFactory
import uz.fayyoz.a1shop.utill.animation.OnCartAnimation
import uz.fayyoz.a1shop.utill.decimalsAfter


class ProductsDetailsFragment :
    BaseFragment<ProductDetailsBinding>(R.layout.product_details), OnCartAnimation {
    private val args by navArgs<ProductsDetailsFragmentArgs>()


    private val productsDetailsVM by viewModels<ProductsDetailsVM> { ViewModelFactory() }

    lateinit var viewPagerAdapter: ImageSliderAdapter

    override fun initViewBinding(view: View): ProductDetailsBinding =
        ProductDetailsBinding.bind(view)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        val bottomNav: BottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation)

        bottomNav.isGone = true

        viewPagerAdapter = ImageSliderAdapter(requireContext(), args.product.images)

        binding.apply {

            viewpager.adapter = viewPagerAdapter
            indicator.setViewPager(binding.viewpager)

            ratingBar.rating = generateRandomNumber()
            ratingTv.text = ratingBar.rating.toString()
            likesTv.text = generateRandomNumber().decimalsAfter(1).toString() + "M"


            titleCardView.animate().translationZ(20f).duration = 1000
            descriptionCardView.animate().translationZ(20f).duration = 1500
            priceCardView.animate().translationZ(40f).duration = 2500


            addToCartButton.setOnClickListener {
                productsDetailsVM.addOnCartProduct(args.product)


                addAnim(root)

            }

        }
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

    private fun generateRandomNumber(): Float {
        return ((Math.random() * 5)).toFloat()
    }

}
