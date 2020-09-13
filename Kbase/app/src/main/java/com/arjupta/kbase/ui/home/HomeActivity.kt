package com.arjupta.kbase.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arjupta.kbase.R
import dagger.Component
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject lateinit var info: Info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMagicBox.create().poke(this);
//        val model : HomeViewModel by viewModels()
    }
}

@Component
interface MagicBox{
    fun poke(app: HomeActivity)
}

class Info @Inject constructor() {
    val text = "Hello Dagger 2"
}