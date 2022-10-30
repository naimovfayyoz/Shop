package uz.fayyoz.a1shop.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isGone
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import uz.fayyoz.a1shop.R
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import uz.fayyoz.a1shop.databinding.ActivityMainBinding
import uz.fayyoz.a1shop.model.User
import uz.fayyoz.a1shop.ui.login.LoginVM
import uz.fayyoz.a1shop.utill.ViewModelFactory
import uz.fayyoz.a1shop.utill.isNull
import uz.fayyoz.a1shop.utill.log


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var navHeader: View


    private var _binding: ActivityMainBinding? = null
    val binding: ActivityMainBinding get() = _binding!!

    private val loginVM by viewModels<LoginVM> { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.isGone = true


        loginVM.getAccessTokens().asLiveData().observe(this) { token ->
            setupNavigation(token)
            lifecycleScope.launchWhenStarted {

                loginVM.getUserData(token!!)
            }

        }
    }

//    private fun getUserData(token: String?) {
//        lifecycleScope.launchWhenStarted {
//            if (!loginVM.getAccessTokens().isNull()) {
//                if (loginVM.getUserData(token!!).isSuccessful) {
//                    val user: User = loginVM.getUserData("Bearer " + token)
//                        .body()!!
//                    setHeader(user)
//
//                } else if (loginVM.getUserData(token).code() == 401) {
//                    refreshToken()
//                    loginVM.getAccessTokens().asLiveData().observe(this@MainActivity)
//                    { token ->
//                        lifecycleScope.launchWhenStarted {
//                            val user: User = loginVM.getUserData("Bearer " + token).body()!!
//                            setHeader(user)
//
//                        }
//                    }
//                }
//            }
//        }
//    }

    private suspend fun refreshToken() {
        loginVM.login("john@mail.com", "changeme").observe(this@MainActivity) { token ->
            lifecycleScope.launchWhenStarted {
                loginVM.saveAccessTokens(token.access_token)
            }
        }
    }


    private fun setupNavigation(accessToken: String?) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.main_graph)
        if (accessToken.isNull()) {
            navGraph.setStartDestination(R.id.loginFragment)

        } else {
            navGraph.setStartDestination(R.id.allProductsFragment)
            setupWithNavController(findViewById<BottomNavigationView>(R.id.bottom_navigation),
                navController)
            setupNavDrawer(accessToken)
            binding.bottomNavigation.isGone = false

        }
        navController.graph = navGraph
    }

    private fun setupNavDrawer(accessToken: String?) {
        binding.apply {

            toggle = ActionBarDrawerToggle(this@MainActivity,
                drawerLayout,
//TODO change str
                R.string.could_not_refresh,
                R.string.refresh)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.firstItem -> {
                        Toast.makeText(this@MainActivity,
                            "First Item Clicked",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.secondtItem -> {
                        Toast.makeText(this@MainActivity,
                            "Second Item Clicked",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.thirdItem -> {
                        lifecycleScope.launch {
                            loginVM.clearAccessToken()
                        }
                    }
                }
                true
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setHeader(user: User) {
        lifecycleScope.launchWhenStarted {
            navHeader = binding.navView.getHeaderView(0)
            var headerImage = navHeader.findViewById<ImageView>(R.id.imageView)
            var headerEmail = navHeader.findViewById<TextView>(R.id.email_tv)
            var headerMoney = navHeader.findViewById<TextView>(R.id.money_tv)

            headerEmail.text = user.email
            headerMoney.text = user.money.toString() + "$"
            Log.d("TAG", "setHeader: "+user.money)


        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
