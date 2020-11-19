package com.gomobi.breakingbad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController

/**
 * Project Name: BreakingBad
 * Author: David White
 * Email: gomobile.dw@gmail.com
 * Date: 19/11/2020
 */

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home))
        navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}