package uz.fayyoz.a1shop.ui.products.product_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import me.relex.circleindicator.CircleIndicator
import me.relex.circleindicator.CircleIndicator2
import me.relex.circleindicator.CircleIndicator3
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.ProductImagePagerBinding
import uz.fayyoz.a1shop.ui.BaseFragment
import uz.fayyoz.a1shop.ui.products.product_details.adapter.ImageSliderAdapter

class ProductsDetailsFragment :
    BaseFragment<ProductImagePagerBinding>(R.layout.product_image_pager) {
    private val args by navArgs<ProductsDetailsFragmentArgs>()


    lateinit var viewPagerAdapter: ImageSliderAdapter

    override fun initViewBinding(view: View): ProductImagePagerBinding =
        ProductImagePagerBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        setUpViews()
        viewPagerAdapter = ImageSliderAdapter(requireContext(), args.product.images)
        binding.apply {

            viewpager.adapter = viewPagerAdapter
            indicator.setViewPager(binding.viewpager)

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


}
