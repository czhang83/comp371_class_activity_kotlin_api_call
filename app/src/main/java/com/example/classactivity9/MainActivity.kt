package com.example.classactivity9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragContainer1, FragmentOne()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.fragContainer2, FragmentTwo()).commit()

    }

}