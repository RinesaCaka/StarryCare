package com.example.starrycare

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox

class Question3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question3, container, false)

        val quotesCheckBox: CheckBox = view.findViewById(R.id.quotesCheckBox)
        val affirmationsCheckBox: CheckBox = view.findViewById(R.id.affirmationsCheckBox)
        val challengesCheckBox: CheckBox = view.findViewById(R.id.challengesCheckBox)

        val sharedPref = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        quotesCheckBox.setOnCheckedChangeListener { _, isChecked ->
            with(sharedPref.edit()) {
                putBoolean("prefQuotes", isChecked)
                apply()
            }
        }

        affirmationsCheckBox.setOnCheckedChangeListener { _, isChecked ->
            with(sharedPref.edit()) {
                putBoolean("prefAffirmations", isChecked)
                apply()
            }
        }

        challengesCheckBox.setOnCheckedChangeListener { _, isChecked ->
            with(sharedPref.edit()) {
                putBoolean("prefChallenges", isChecked)
                apply()
            }
        }

        return view
    }
}