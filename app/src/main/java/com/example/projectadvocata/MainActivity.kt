package com.example.projectadvocata

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.projectadvocata.databinding.ActivityMainBinding
import com.example.projectadvocata.ui.chatbot.ChatbotActivity
import com.example.projectadvocata.ui.profile.ProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navView.background = null
        binding.navView.menu.getItem(2).isEnabled = false

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_lawyer, R.id.navigation_inbox, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Menggunakan setOnItemSelectedListener (pengganti dari setOnNavigationItemSelectedListener)
        navView.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.navigation_profile -> {
                    // Langsung membuka ProfileActivity tanpa melewati fragment
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true // Mengindikasikan item ini sudah diproses
                }
                else -> {
                    // Biarkan navigasi fragment berfungsi seperti biasa
                    navController.navigate(item.itemId)
                    true
                }
            }
        }

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            startActivity(Intent(this, ChatbotActivity::class.java))
        }
    }
}