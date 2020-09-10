package com.arjupta.kbase.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arjupta.kbase.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model : HomeViewModel by viewModels()
    }
}