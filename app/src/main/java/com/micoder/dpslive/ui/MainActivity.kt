package com.micoder.dpslive.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.andremion.floatingnavigationview.FloatingNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.micoder.dpslive.R
import com.micoder.dpslive.authentications.AuthActivity
import com.micoder.dpslive.databinding.ActivityMainBinding
import com.micoder.dpslive.databinding.NavigationViewHeaderBinding
import com.micoder.dpslive.utils.SP

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var headerBinding: NavigationViewHeaderBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var uid: String

    private lateinit var mFloatingNavigationView: FloatingNavigationView

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

        binding.selectClassIV.setOnClickListener {
            showAD()
        }

    }

    private fun showAD() {
        var selectedClassItemIndex = 0
        val classes = arrayOf("Class 1","Class 2","Class 3","Class 4","Class 5","Class 6","Class 7","Class 8","Class 9","Class 10","Class 11","Class 12")
        var selectedClass = classes[selectedClassItemIndex]

        MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Background)
            .setTitle("Classes")
            .setCancelable(false)
            .setSingleChoiceItems(classes, selectedClassItemIndex) {dialog, which ->
                selectedClassItemIndex = which
                selectedClass = classes[which]
            }
            .setPositiveButton("Select") {dialog, which ->
                SP(this).put_data("selectedclass",selectedClass)
                Toast.makeText(this,"$selectedClass Selected", Toast.LENGTH_SHORT).show()
                recreate()
            }
            .show()
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
}