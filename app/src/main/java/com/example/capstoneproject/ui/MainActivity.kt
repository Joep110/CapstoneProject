package com.example.capstoneproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.view.get
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
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.bottom_navigation_menu, menu)
        bottom_navigation.setOnNavigationItemSelectedListener  {item ->
            when(item.itemId) {
                R.id.popular -> {
                    navController.navigate(R.id.action_latestCryptoFragment_to_popularCryptoFragment)
                    item.isEnabled = false
                    true
                }
                R.id.recent -> {
                    menu.findItem(R.id.popular).isEnabled = true
                    item.isEnabled = false
                    navController.navigate(R.id.action_popularCryptoFragment_to_latestCryptoFragment)
                    true
                }
                else -> false
            }
        }
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