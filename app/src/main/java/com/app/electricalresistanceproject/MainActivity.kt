package com.app.electricalresistanceproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load the fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ResistanceFragment())
            .commit()
    }
}