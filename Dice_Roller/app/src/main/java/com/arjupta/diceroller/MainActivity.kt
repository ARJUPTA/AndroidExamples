package com.arjupta.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var diceImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById<Button>(R.id.roll_button)
        rollButton.setOnClickListener{
            if(rollButton.text == resources.getString(R.string.lets_roll)){
                rollButton.text = resources.getString(R.string.roll)
                diceImage.visibility = View.VISIBLE
            }
            rollDice()
        }
        diceImage = findViewById(R.id.dice_image)
        diceImage.visibility = View.GONE
    }

    private fun rollDice() {
        val randomInt = Random().nextInt(6)+1
        val drawableResource = when(randomInt){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.empty_dice
        }
        diceImage.setImageResource(drawableResource)
    }

}