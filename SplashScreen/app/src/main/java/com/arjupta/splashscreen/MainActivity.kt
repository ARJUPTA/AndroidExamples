package com.arjupta.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var book: ImageView
    private lateinit var face: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.common_splash_screen)
        book = findViewById<ImageView>(R.id.icon_book)
        face = findViewById<ImageView>(R.id.icon_face)
        val faceFade = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in_face)
        val bookFade = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in_book)
        face.startAnimation(faceFade)
        book.startAnimation(bookFade)
    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed({
            val intent = Intent(this, Main2Activity::class.java)
                startActivity(intent)
                finish()
        }, 2000)
    }
}
