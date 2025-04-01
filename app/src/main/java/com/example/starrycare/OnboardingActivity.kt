package com.example.starrycare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabDots: TabLayout
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        tabDots = findViewById(R.id.tabDots)
        nextButton = findViewById(R.id.nextButton)

        val fragments = listOf(
            Question1Fragment(),
            Question2Fragment(),
            Question3Fragment()
        )
        val adapter = OnboardingPagerAdapter(this, fragments)
        viewPager.adapter = adapter

        TabLayoutMediator(tabDots, viewPager) { _, _ -> }.attach()

        nextButton.setOnClickListener {
            val currentItem = viewPager.currentItem
            if (currentItem < fragments.size - 1) {
                viewPager.currentItem = currentItem + 1
            } else {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                nextButton.text = if (position == fragments.size - 1) "Start Glowing" else "Next"
            }
        })
    }
}

class OnboardingPagerAdapter(
    activity: AppCompatActivity,
    private val fragments: List<Fragment>
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]
}