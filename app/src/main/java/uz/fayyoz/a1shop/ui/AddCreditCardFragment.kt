package uz.fayyoz.a1shop.ui

import android.view.View
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.CreditCardFragmentBinding

class AddCreditCardFragment :
    BaseFragment<CreditCardFragmentBinding>(R.layout.credit_card_fragment) {

    override fun initViewBinding(view: View): CreditCardFragmentBinding =
        CreditCardFragmentBinding.bind(view)


}