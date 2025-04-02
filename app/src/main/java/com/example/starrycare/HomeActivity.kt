package com.example.starrycare

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val selfCareGoal = sharedPref.getString("selfCareGoal", "Relax") ?: "Relax"

        // Get a random quote based on the user's goal
        val quotes = QuoteRepository.getQuotesByCategory(selfCareGoal)
        val quoteTextView: TextView = findViewById(R.id.quoteTextView)
        quoteTextView.text = quotes.randomOrNull()?.text ?: "Welcome to your glow-up journey!"
    }
}