package com.micoder.dpslive.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.andremion.floatingnavigationview.FloatingNavigationView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.micoder.dpslive.R
import com.micoder.dpslive.authentications.AuthActivity
import com.micoder.dpslive.databinding.ActivityMainBinding
import com.micoder.dpslive.databinding.NavigationViewHeaderBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var headerBinding: NavigationViewHeaderBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var uid: String

    private lateinit var mFloatingNavigationView: FloatingNavigationView

    private lateinit var switchMaterial: SwitchMaterial
    private val MyPREFERENCES: String = "nightModePrefs"
    private val KEY_ISNIGHTMODE: String = "isNightMode"
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initializing for navHeader
        val viewHeader = binding.floatingNavigationView.getHeaderView(0)
        headerBinding = NavigationViewHeaderBinding.bind(viewHeader)

        // Firebase Stuff's
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = Firebase.database
        databaseReference = firebaseDatabase.reference
        val firebaseUser = firebaseAuth.currentUser
        uid = firebaseUser!!.uid

        bottomNavigation()

        fab()
        navigationHeader()

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

            when (item.itemId) {
                R.id.logOutFab -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Logout")
                    builder.setMessage("Are you sure want to Logout")

                    builder.setPositiveButton("Yes") { dialog, which ->
                        try {
                            firebaseAuth.signOut()
                            startActivity(Intent(this@MainActivity, AuthActivity::class.java))
                            finish()
                            Toast.makeText(this, "User Logged out!", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(this, "onClick: Exception " + e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                    builder.setNegativeButton("No") { dialog, which ->
                        return@setNegativeButton
                    }
                    builder.show()
                    mFloatingNavigationView.close()
                }
            }

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

    // Retrieve & Display User Data in NavHeader
    private fun navigationHeader() {
        databaseReference.child("users").child(uid).get().addOnSuccessListener {
            if (it.exists()) {
                val userName = it.child("name").value.toString()
                val userEmail = it.child("email").value.toString()
                headerBinding.profileFabName.text = userName
                headerBinding.profileFabEmail.text = userEmail
            }
        }.addOnFailureListener {
            val error = it.message.toString()
            Toast.makeText(this@MainActivity,error, Toast.LENGTH_SHORT).show()
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