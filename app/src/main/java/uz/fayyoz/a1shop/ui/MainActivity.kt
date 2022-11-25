package uz.fayyoz.a1shop.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isGone
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import kotlinx.coroutines.launch
import uz.fayyoz.a1shop.R
import uz.fayyoz.a1shop.databinding.ActivityMainBinding
import uz.fayyoz.a1shop.model.User
import uz.fayyoz.a1shop.ui.login.LoginVM
import uz.fayyoz.a1shop.utill.*


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var navHeader: View


    private var _binding: ActivityMainBinding? = null
    val binding: ActivityMainBinding get() = _binding!!

    private val loginVM by viewModels<LoginVM> { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.isGone = true


        loginVM.getAccessTokens().asLiveData().observe(this) { token ->
            setupNavigation(token)
            lifecycleScope.launchWhenStarted {
                setHeader(loginVM.getUserData(), token)


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
            setupWithNavController(binding.bottomNavigation,
                navController)
            val badge = binding.bottomNavigation.getOrCreateBadge(R.id.allProductsFragment)
            badge.isVisible = true
// An icon only badge will be displayed unless a number is set:
            badge.number = 99
            setupNavDrawer()
            binding.bottomNavigation.isGone = false

        }
        navController.graph = navGraph
    }

    private fun setupNavDrawer() {
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
                        val navHostFragment =
                            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                        navController = navHostFragment.navController

                        navController
                            .navigate(R.id.action_allProductsFragment_to_addCreditCardFragment)
                    }
                    R.id.secondtItem -> {
                        Toast.makeText(this@MainActivity,
                            "Second Item Clicked",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.thirdItem -> {
                        lifecycleScope.launch {
                            loginVM.apply {
                                clearAccessToken()
                                deleteUser()
                            }
                        }
                    }
                }
                true
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setHeader(user: User, token: String?) {
        lifecycleScope.launchWhenStarted {
            navHeader = binding.navView.getHeaderView(0)
            navHeader.setBackgroundColor(R.drawable.background_nav_header)
            val headerImage = navHeader.findViewById<ImageView>(R.id.imageView)
            val headerEmail = navHeader.findViewById<TextView>(R.id.email_tv)
            val headerMoney = navHeader.findViewById<TextView>(R.id.money_tv)

            if (!token.isNull()) {
                val errImg = getDrawable(this@MainActivity, R.drawable.error_image)

                headerImage.setImageRemoteCircled(user.avatar, errImg)
                headerEmail.text = user.email
                headerMoney.text = user.money.toString() + "$"
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
