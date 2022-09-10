package uz.fayyoz.a1shop.ui.signUp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.SignupFragmentBinding
import uz.fayyoz.a1shop.ui.BaseFragment
import uz.fayyoz.a1shop.utill.ViewModelFactory
import uz.fayyoz.a1shop.utill.enable
import uz.fayyoz.a1shop.utill.visible

class SignUpFragment : BaseFragment<SignupFragmentBinding>(R.layout.signup_fragment) {
    override fun initViewBinding(view: View): SignupFragmentBinding =
        SignupFragmentBinding.bind(view)

    private val signUpVM by viewModels<SignUpVM> { ViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visible(false)
        binding.registerBtn.enable(false)

        binding.registerBtn.setOnClickListener {
            binding.progressBar.visible(true)
            val name = binding.emailEt.text.toString()
            val email = binding.emailEt.text.toString().trim()
            val password = binding.passwordEt.text.toString().trim()
            signUpVM.signUp(name, email, password)
        }

        binding.passwordEt.addTextChangedListener {
            val name = binding.nameEt.text.toString()
            val email = binding.emailEt.text.toString().trim()
            binding.registerBtn.enable(Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    && email.isNotEmpty()
                    && it.toString().trim().isNotEmpty()
                    && name.isNotEmpty())
        }

        binding.btnLogRegister.setOnClickListener()
        {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

    }

}