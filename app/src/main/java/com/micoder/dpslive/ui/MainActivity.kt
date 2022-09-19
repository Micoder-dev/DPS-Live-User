package com.micoder.dpslive.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.andremion.floatingnavigationview.FloatingNavigationView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.micoder.dpslive.R
import com.micoder.dpslive.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mFloatingNavigationView: FloatingNavigationView

    private lateinit var switchMaterial: SwitchMaterial
    private val MyPREFERENCES: String = "nightModePrefs"
    private val KEY_ISNIGHTMODE: String = "isNightMode"
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigation()

        fab()

        dayNight()

    }

    // Bottom Navigation View
    private fun bottomNavigation() {
        val fragment = supportFragmentManager.findFragmentById(R.id.dpsNavHostFragment)
        binding.bottomNavigationView.setupWithNavController(fragment!!.findNavController())
    }

    // Floating Navigation View
    private fun fab() {
        mFloatingNavigationView = binding.floatingNavigationView
        mFloatingNavigationView.setOnClickListener { mFloatingNavigationView.open() }
        mFloatingNavigationView.setNavigationItemSelectedListener { item ->
            Toast.makeText(this, item.title.toString() + " Selected!", Toast.LENGTH_SHORT).show()
            mFloatingNavigationView.close()
            true
        }
    }
    override fun onBackPressed() {
        if (mFloatingNavigationView.isOpened) {
            mFloatingNavigationView.close()
        } else {
            super.onBackPressed()
        }
    }

    // Day Night themes
    private fun dayNight() {
        // Shared pref
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)

        // navigationView night mode switch control
        val menuItem = mFloatingNavigationView.menu.findItem(R.id.nightModeFab) // first initialize MenuItem

        switchMaterial = menuItem.actionView.findViewById<View>(R.id.drawer_switch) as SwitchMaterial

        checkNightModeActivated()

        switchMaterial.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                Toast.makeText(this, "Dark Mode Enabled", Toast.LENGTH_SHORT).show()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                saveNightModeState(true)
                recreate()
            } else {
                Toast.makeText(this, "Dark Mode Disabled", Toast.LENGTH_SHORT).show()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                saveNightModeState(false)
                recreate()
            }
        }
    }
    private fun saveNightModeState(nightMode: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(KEY_ISNIGHTMODE, nightMode)
        editor.apply()
    }
    private fun checkNightModeActivated() {
        if (sharedPreferences.getBoolean(KEY_ISNIGHTMODE, false)) {
            switchMaterial.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            switchMaterial.isChecked = false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}