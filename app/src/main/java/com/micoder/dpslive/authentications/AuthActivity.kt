package com.micoder.dpslive.authentications

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.micoder.dpslive.R
import com.micoder.dpslive.databinding.ActivityAuthBinding
import com.micoder.dpslive.ui.MainActivity

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    lateinit var signUp: TextView
    lateinit var logIn: TextView
    lateinit var signUpLayout: LinearLayout
    lateinit var logInLayout: LinearLayout
    lateinit var authBtn: Button

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()

        logIn.setOnClickListener { gotoLogin() }
        signUp.setOnClickListener { gotoSignUp() }
        authBtn.setOnClickListener { gotoAuth() }

    }

    private fun initVariables() {
        signUp = binding.signUp
        logIn = binding.logIn
        signUpLayout = binding.signUpLayout
        logInLayout = binding.logInLayout
        authBtn = binding.authBtn
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun gotoLogin() {
        signUpLayout.visibility = View.GONE
        logInLayout.visibility = View.VISIBLE
        signUp.background = null
        signUp.setTextColor(resources.getColor(R.color.orange,null))
        logIn.setTextColor(resources.getColor(R.color.textColor,null))
        logIn.background = resources.getDrawable(R.drawable.switch_trcks,null)
        authBtn.setText("Login")
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun gotoSignUp() {
        logInLayout.visibility = View.GONE
        signUpLayout.visibility = View.VISIBLE
        logIn.background = null
        signUp.setTextColor(resources.getColor(R.color.textColor,null))
        logIn.setTextColor(resources.getColor(R.color.orange,null))
        signUp.background = resources.getDrawable(R.drawable.switch_trcks,null)
        authBtn.setText("Register")
    }

    private fun gotoAuth() {
        startActivity(Intent(this@AuthActivity,MainActivity::class.java))
    }
}