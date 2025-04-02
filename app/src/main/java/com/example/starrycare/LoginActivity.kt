package com.example.starrycare

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import android.util.Log

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LoginActivity", "onCreate called")

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            Log.d("LoginActivity", "User is logged in: ${currentUser.email}")
            val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            val isFirstLogin = sharedPref.getBoolean("isFirstLogin", true)

            if (isFirstLogin) {
                Log.d("LoginActivity", "First login, redirecting to OnboardingActivity")
                with(sharedPref.edit()) {
                    putBoolean("isFirstLogin", false)
                    apply()
                }
                val intent = Intent(this, OnboardingActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.d("LoginActivity", "Not first login, redirecting to MainActivity")
                val intent = Intent(this, HomeActivity::class.java) // Fixed from HomeActivity
                startActivity(intent)
                finish()
            }
            return
        }
        Log.d("LoginActivity", "No user logged in, showing login UI")
        setContentView(R.layout.login)

        val emailField = findViewById<EditText>(R.id.email)
        val passwordField = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login_button)
        val registerLink = findViewById<TextView>(R.id.register)

        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loginUser(email, password)
        }

        registerLink.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }


    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                    val isFirstLogin = sharedPref.getBoolean("isFirstLogin", true)

                    if (isFirstLogin) {
                        with(sharedPref.edit()) {
                            putBoolean("isFirstLogin", false)
                            apply()
                        }
                        val intent = Intent(this, OnboardingActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    finish()
                } else {
                    Toast.makeText(this, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}