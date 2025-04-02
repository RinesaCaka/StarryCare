package com.example.starrycare

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup

class Question1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question1, container, false)

        val goalsRadioGroup: RadioGroup = view.findViewById(R.id.goalsRadioGroup)
        goalsRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val goal = when (checkedId) {
                R.id.relaxOption -> "Relax"
                R.id.glowOption -> "Glow"
                else -> "Empower"
            }
            // Save to SharedPreferences
            val sharedPref = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("selfCareGoal", goal)
                apply()
            }
        }

        return view
    }
}