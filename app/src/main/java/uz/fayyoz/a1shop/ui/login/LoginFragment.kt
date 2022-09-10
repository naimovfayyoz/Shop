package uz.fayyoz.a1shop.ui.login

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.LoginFragmentBinding
import uz.fayyoz.a1shop.ui.BaseFragment
import uz.fayyoz.a1shop.utill.*


class LoginFragment : BaseFragment<LoginFragmentBinding>(R.layout.login_fragment) {

    override fun initViewBinding(view: View): LoginFragmentBinding = LoginFragmentBinding.bind(view)
    private val loginVM by viewModels<LoginVM> { ViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visible(false)
        binding.loginBtn.enable(false)

        binding.loginBtn.setOnClickListener {
            binding.progressBar.visible(true)
            val email = binding.emailEt.text.toString().trim()
            val password = binding.passwordEt.text.toString().trim()
           checkToken(email, password)
        }

        binding.passwordEt.addTextChangedListener {
            val email = binding.emailEt.text.toString().trim()
            binding.loginBtn.enable(Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    && email.isNotEmpty()
                    && it.toString().trim().isNotEmpty())
        }

        binding.signupBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_signUpFragment)
        }
    }

    private fun checkToken(email: String, password: String) {

        lifecycleScope.launchWhenStarted() {
            loginVM.login(email, password).observe(viewLifecycleOwner) {
                if (!it.access_token.isNull()) {
                    lifecycleScope.launchWhenStarted {
                        loginVM.saveAccessTokens(it.access_token)
                    }
                } else {
                    Toast.makeText(requireContext(), "invalid login", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }















//@Todo use in validation
//                    binding.emailEt.error = "email cannot be blank"
//                    binding.passwordEd.error = ""
    //@TODO implement it by itself
//    fun isValidEmail(target: CharSequence?): Boolean {
//        return if (TextUtils.isEmpty(target)) {
//            false
//        } else {
//            Patterns.EMAIL_ADDRESS.matcher(target).matches()
//        }
//    }
}
