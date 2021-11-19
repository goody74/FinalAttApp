package com.og.randomizer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kotlin.random.Random


//константа для ключа сохранения состояния
private const val LAST_RANDOM_VALUE = "LAST_RANDOM_VALUE"
private const val LAST_RANDOM_VALUE_R = "LAST_RANDOM_VALUE_RIGHT"

class DiceFragment : Fragment() {

    //ранняя инициализация переменных для вью
    private lateinit var diceImageView: ImageView
    private lateinit var diceImageViewR: ImageView
    private lateinit var randomizeButton: Button

    private var randomValue = 1
    private var randomValueR = 6

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dice, container, false)

        diceImageView = view.findViewById(R.id.dice_image_view)
        diceImageViewR = view.findViewById(R.id.dice_image_view_r)

        randomizeButton = view.findViewById(R.id.randomize_button)

        savedInstanceState?.let {
            randomValue = it.getInt(LAST_RANDOM_VALUE)
            randomValueR = it.getInt(LAST_RANDOM_VALUE_R)
        }

        rollDice(diceImageView,randomValue)
        rollDice(diceImageViewR,randomValueR)

        randomizeButton.setOnClickListener {
            randomValue = Random.nextInt(1, 6)
            randomValueR = Random.nextInt(1, 6)
            rollDice(diceImageView,randomValue)
            rollDice(diceImageViewR,randomValueR)
        }

        return view
    }

    //подкидывает кубик
    private fun rollDice(imageView: ImageView, randomValue: Int) {
        imageView.setImageResource(
            when (randomValue) {
                1 -> R.drawable.dice1
                2 -> R.drawable.dice2
                3 -> R.drawable.dice3
                4 -> R.drawable.dice4
                5 -> R.drawable.dice5
                6 -> R.drawable.dice6
                else -> R.drawable.dice1
            }
        )
    }

    //сохраняет состояние
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(LAST_RANDOM_VALUE, randomValue)
    }

}