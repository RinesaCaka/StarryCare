package com.example.starrycare

object QuoteRepository {
    private val quotes = listOf(
        Quote("Serenity is a warm bath away.", "Relax"),
        Quote("Shine bright, your glow-up is here!", "Glow"),
        Quote("You’re unstoppable, keep shining!", "Empower"),
        Quote("Take a deep breath, you’ve got this.", "Relax"),
        Quote("Your beauty radiates from within.", "Glow"),
        Quote("You’re a total star, babe!", "Empower")
    )

    fun getQuotesByCategory(category: String): List<Quote> {
        return quotes.filter { it.category == category }
    }
}