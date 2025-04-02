package com.example.starrycare

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Switch

class Question2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question2, container, false)

        val timeRadioGroup: RadioGroup = view.findViewById(R.id.timeRadioGroup)
        val reminderSwitch: Switch = view.findViewById(R.id.reminderSwitch)

        timeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val time = when (checkedId) {
                R.id.morningOption -> "Morning"
                R.id.afternoonOption -> "Afternoon"
                else -> "Evening"
            }
            val sharedPref = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("preferredTime", time)
                apply()
            }
        }

        reminderSwitch.setOnCheckedChangeListener { _, isChecked ->
            val sharedPref = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putBoolean("remindersEnabled", isChecked)
                apply()
            }
        }

        return view
    }
}