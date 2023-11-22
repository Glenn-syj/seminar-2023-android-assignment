package com.wafflestudio.assignment4

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.wafflestudio.assignment4.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val loginPref = sharedPreferences.getBoolean("loginResult", false)
        Log.d("MA", "$loginPref")
        if (!loginPref) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentView.id, LoginFragment()) // YourFragment()는 추가할 Fragment입니다.
                .commit()
        } else {
            replaceHomeFragment()
        }


    }
    private fun replaceHomeFragment() {
        Log.d("MA", "changed to HomeFragment")
        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentView.id, homeFragment)
            .commit()
    }
}