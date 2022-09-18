package uz.fayyoz.a1shop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import uz.fayyoz.a1shop.R
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import uz.fayyoz.a1shop.data.local.pref.UserPref
import uz.fayyoz.a1shop.utill.isNull
import java.lang.IllegalArgumentException


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {


        val userPref = UserPref(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        lifecycleScope.launch {
//            userPref.clear()
//        }
        userPref.accessToken.asLiveData().observe(this)
        {
            setupNavigation(it.isNull())

            setupWithNavController(findViewById<BottomNavigationView>(R.id.bottom_navigation), navController)

        }
    }

    private fun setupNavigation(isAuthorized: Boolean) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.main_graph)
        if (isAuthorized) {
            navGraph.setStartDestination(R.id.loginFragment)

        } else {
            navGraph.setStartDestination(R.id.allProductsFragment)
        }
        navController.graph = navGraph
    }
}
