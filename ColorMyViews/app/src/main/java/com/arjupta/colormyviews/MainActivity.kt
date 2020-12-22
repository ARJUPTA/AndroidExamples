package com.arjupta.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners(){
        val clickableViews : List<View> = listOf(
            findViewById(R.id.box_one),
            findViewById(R.id.box_two),
            findViewById(R.id.box_three),
            findViewById(R.id.box_four),
            findViewById(R.id.box_five),
            findViewById(R.id.constraint_layout),
            findViewById(R.id.red_btn),
            findViewById(R.id.green_btn),
            findViewById(R.id.yellow_btn)
        )

        for(item in clickableViews){
            item.setOnClickListener{ colorIt(it)}
        }
    }

    private fun colorIt(view: View){
        when(view.id){
            R.id.box_one -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two -> view.setBackgroundColor(Color.GRAY)
            R.id.box_three -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.box_four -> view.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.box_five -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.red_btn -> (findViewById<TextView>(R.id.box_three)).setBackgroundResource(R.color.my_red)
            R.id.green_btn -> (findViewById<TextView>(R.id.box_four)).setBackgroundResource(R.color.my_green)
            R.id.yellow_btn -> (findViewById<TextView>(R.id.box_five)).setBackgroundResource(R.color.my_yellow)
            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}