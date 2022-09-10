package uz.fayyoz.a1shop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import uz.fayyoz.a1shop.R
import androidx.navigation.NavController
import uz.fayyoz.a1shop.data.local.pref.UserPref
import uz.fayyoz.a1shop.utill.isNull



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
            navGraph.setStartDestination(R.id.mainFragment)
        }
        navController.graph = navGraph
    }
}