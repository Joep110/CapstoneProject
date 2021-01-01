package com.example.capstoneproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.capstoneproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val viewModel: CryptoValueViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        navController = findNavController(R.id.nav_host_fragment)

        bottom_navigation.selectedItemId = R.id.recent
        bottom_navigation.setOnNavigationItemSelectedListener  {item ->
            when(item.itemId) {
                R.id.popular -> {
                    bottom_navigation.menu.findItem(R.id.recent).isEnabled = true
                    bottom_navigation.menu.findItem(R.id.popular).isEnabled = false
                    navController.navigate(R.id.action_latestCryptoFragment_to_popularCryptoFragment)
                    true
                }
                R.id.recent -> {
                    bottom_navigation.menu.findItem(R.id.recent).isEnabled = false
                    bottom_navigation.menu.findItem(R.id.popular).isEnabled = true
                    navController.navigate(R.id.action_popularCryptoFragment_to_latestCryptoFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.bottom_navigation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}