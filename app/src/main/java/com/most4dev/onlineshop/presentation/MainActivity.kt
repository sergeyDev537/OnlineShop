package com.most4dev.onlineshop.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.databinding.ActivityMainBinding
import com.most4dev.onlineshop.utils.cornersBottomNavigationView

class MainActivity : AppCompatActivity() {

    private val navigationView: NavigationView by lazy {
        binding.navView
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navigationViewBottom: BottomNavigationView by lazy {
        binding.appBarMain.contentMain.bottomNavigationView
    }

    private val drawerLayout: DrawerLayout by lazy {
        binding.drawerLayout
    }

    private var menuToolbar: Menu? = null

    private val navController by lazy {
        findNavController(R.id.nav_host_fragment_drawer)
    }

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.nav_bottom_home,
                R.id.nav_bottom_favorite,
                R.id.nav_bottom_cart,
                R.id.nav_bottom_chat,
                R.id.nav_bottom_profile
            ), drawerLayout
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSearchViewListener()
        setSupportActionBar(binding.appBarMain.toolbar)
        setCornersBottomNavigation()
        setDestinationController()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)
        navigationViewBottom.setupWithNavController(navController)

    }

    private fun setSearchViewListener() {
        binding.appBarMain.toolbarSearch.setOnQueryTextFocusChangeListener(
            object : SearchView.OnQueryTextListener,
                View.OnFocusChangeListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

                override fun onFocusChange(v: View?, hasFocus: Boolean) {
                    if (hasFocus) {
                        navController.navigate(R.id.action_nav_bottom_home_to_searchFragment)
                    }

                }

            })
    }

    private fun setDestinationController() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.label) {
                getString(R.string.home) -> {
                    visibleIconProfile(true)
                }
                getString(R.string.favorite) -> {
                    visibleIconProfile(false)
                }
                getString(R.string.cart) -> {
                    visibleIconProfile(false)
                }
                getString(R.string.chat) -> {
                    visibleIconProfile(false)
                }
                getString(R.string.profile) -> {
                    visibleIconProfile(false)
                }
                getString(R.string.about_product) -> {
                    visibleIconProfile(false)
                    binding.appBarMain.toolbar.visibility = View.GONE
                }
            }

        }
    }

    private fun visibleIconProfile(visible: Boolean) {
        if (visible) {
            binding.appBarMain.toolbar.visibility = View.VISIBLE
            binding.appBarMain.toolbarSearch.visibility = View.VISIBLE
            menuToolbar?.let {
                it.findItem(R.id.menu_search).isVisible = true
            }
        } else {
            binding.appBarMain.toolbar.visibility = View.VISIBLE
            binding.appBarMain.toolbarSearch.visibility = View.GONE
            menuToolbar?.let {
                it.findItem(R.id.menu_search).isVisible = false
            }
        }

    }

    private fun setCornersBottomNavigation() {
        binding.appBarMain.contentMain.bottomNavigationView.cornersBottomNavigationView(
            resources,
            R.dimen.fab_margin
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuToolbar = menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_drawer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    companion object {

        fun newInstance(activity: Activity): Intent {
            return Intent(activity, MainActivity::class.java)
        }

    }
}