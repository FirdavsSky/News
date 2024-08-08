package com.example.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.news.fragments.HistoryFragment
import com.example.news.fragments.HomeFragment
import com.example.news.fragments.OptionFragment
import com.example.news.fragments.StarFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var bottomNavigationView: BottomNavigationView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()


        initBottomNavigationView()

    }

   private fun initBottomNavigationView(){
       bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView?.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, HomeFragment())
                        .commit()
                }
                R.id.history -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, HistoryFragment())
                        .commit()
                }
                R.id.star -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, StarFragment())
                        .commit()
                }
                R.id.option -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, OptionFragment())
                        .commit()
                }
            }
            true
        }
    }
}