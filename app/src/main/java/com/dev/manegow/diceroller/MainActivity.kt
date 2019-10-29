package com.dev.manegow.diceroller

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import java.lang.Exception
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var firstDiceImage : ImageView
    lateinit var secondDiceImage : ImageView
    lateinit var movementList : ListView
    var plusVal : Int = 0
    val movementsListItems = mutableListOf<String>()
    var movementSet : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton : Button = findViewById(R.id.roll_button)
        firstDiceImage = findViewById(R.id.dice_image)
        secondDiceImage = findViewById(R.id.dice_second_image)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        movementList = findViewById(R.id.movement_list)
        rollButton.setOnClickListener {
            rollButton.visibility = View.VISIBLE
            rollFirstDice()
            rollSecondDice()
            plusVal = 0
        }
    }

    private fun DrawableRes(): Int {
        var randomInt : Int
        var drawableResource : Int
        try {
            randomInt = Random.nextInt(6) + 1
            plusVal += randomInt
            drawableResource = when(randomInt)
            {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            return drawableResource
        }
        catch (e: Exception) {
            Log.println(Log.ERROR, "Error", "Error al obtener número random")
            return 0
        }
        finally {
            randomInt = 0
            drawableResource = 0
        }
    }

    private fun rollFirstDice() {
        val drawableResource = DrawableRes()
        firstDiceImage.setImageResource(drawableResource)
    }

    private fun rollSecondDice() {
        val drawableResource = DrawableRes()
        addItemToMovementList(plusVal)
        secondDiceImage.setImageResource(drawableResource)

    }

    private fun addItemToMovementList(int: Int) {
        movementSet += 1
        val movementText = "Movement $movementSet : $int"
        movementsListItems.add(movementText)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, movementsListItems)
        movementList.adapter = adapter
        movementList.setSelection(movementList.adapter.count -1)
    }
}
