package com.micoder.dpslive.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.andremion.floatingnavigationview.FloatingNavigationView
import com.micoder.dpslive.R
import com.micoder.dpslive.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mFloatingNavigationView: FloatingNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = supportFragmentManager.findFragmentById(R.id.dpsNavHostFragment)
        binding.bottomNavigationView.setupWithNavController(fragment!!.findNavController())

        fab()

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
}