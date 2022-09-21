package com.micoder.dpslive.authentications

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.micoder.dpslive.R
import com.micoder.dpslive.databinding.ActivityAuthBinding
import com.micoder.dpslive.ui.MainActivity

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    lateinit var signUp: TextView
    lateinit var logIn: TextView
    lateinit var signUpLayout: LinearLayout
    lateinit var logInLayout: LinearLayout

    lateinit var userName: EditText
    lateinit var signupEmail: EditText
    lateinit var signupPassword: EditText
    lateinit var signupConfirmPassword: EditText

    lateinit var loginEmail: EditText
    lateinit var loginPassword: EditText

    lateinit var authBtn: Button

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    private lateinit var progressBar: ProgressBar

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()

        checkUser()

        logIn.setOnClickListener { gotoLogin() }
        signUp.setOnClickListener { gotoSignUp() }
        authBtn.setOnClickListener { gotoAuth() }

    }

    private fun initVariables() {
        signUp = binding.signUp
        logIn = binding.logIn
        signUpLayout = binding.signUpLayout
        logInLayout = binding.logInLayout

        userName = binding.userName
        signupEmail = binding.signupEmail
        signupPassword = binding.signupPassword
        signupConfirmPassword = binding.signupConfirmPassword

        loginEmail = binding.loginEmail
        loginPassword = binding.loginPassword

        authBtn = binding.authBtn

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = Firebase.database
        databaseReference = firebaseDatabase.reference.child("users")

        progressBar = binding.progressBar
    }

    private fun checkUser() {

        // check if user is already logged in or not
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            // user is already logged in
            // start Main activity
            startActivity(Intent(this@AuthActivity, MainActivity::class.java))
            finish()
        }

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
        val authBtnText = authBtn.text.toString()
        if (authBtnText == "Login") {
            // Login Button Clicked
            progressBar.visibility = View.VISIBLE
            authBtn.visibility = View.GONE
            loginUser()
        } else {
            // Register Button Clicked
            progressBar.visibility = View.VISIBLE
            authBtn.visibility = View.GONE
            registerUser()
        }
    }

    private fun loginUser() {
        val email = loginEmail.text.toString()
        val password = loginPassword.text.toString()

        if (email.isBlank() || password.isBlank()) {
            progressBar.visibility = View.GONE
            authBtn.visibility = View.VISIBLE
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                progressBar.visibility = View.GONE
                authBtn.visibility = View.VISIBLE
                startActivity(Intent(this@AuthActivity,MainActivity::class.java))
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                progressBar.visibility = View.GONE
                authBtn.visibility = View.VISIBLE
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            progressBar.visibility = View.GONE
            authBtn.visibility = View.VISIBLE
            val error = it.toString()
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerUser() {
        val name = userName.text.toString()
        val email = signupEmail.text.toString()
        val password = signupPassword.text.toString()
        val confirmPassword = signupConfirmPassword.text.toString()

        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            progressBar.visibility = View.GONE
            authBtn.visibility = View.VISIBLE
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        if (password != confirmPassword) {
            progressBar.visibility = View.GONE
            authBtn.visibility = View.VISIBLE
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                progressBar.visibility = View.GONE
                authBtn.visibility = View.VISIBLE

                startActivity(Intent(this@AuthActivity,MainActivity::class.java))
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()

                val firebaseUser = firebaseAuth.currentUser
                val uid = firebaseUser!!.uid
                // 👇 create a map of value
                val profileMap: HashMap<String, Any> = HashMap()
                profileMap["name"] = name.toString()
                profileMap["email"] = email.toString()
                profileMap["password"] = password.toString()
                profileMap["uid"] = uid.toString()
                // 👇 write to database
                databaseReference.child(firebaseUser.uid).updateChildren(profileMap)
                Toast.makeText(this@AuthActivity, "$name your user info updated",Toast.LENGTH_SHORT).show()
                finish()

            } else {
                progressBar.visibility = View.GONE
                authBtn.visibility = View.VISIBLE
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            progressBar.visibility = View.GONE
            authBtn.visibility = View.VISIBLE
            val error = it.toString()
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

    }


}