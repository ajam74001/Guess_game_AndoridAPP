package com.example.myfirstapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.myfirstapplication.databinding.ActivityMainBinding
import java.lang.Exception
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
        var answer= 0
        var game_over = false
        var num_attempts = 0
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start_over()

    }

    fun generateAnswer(){
        answer= Random.nextInt(1,25)
    }

    fun start_over(){
        game_over= false
        num_attempts = 0
        generateAnswer()
        val answerTextView  = findViewById<TextView>(R.id.answer)
        answerTextView.text = "??"
    }

    fun btnStartOverTapped(view: View){

        start_over()
    }
    fun btnSubmit(view: View){
        game_over= true
        num_attempts++
        val user_guess = getUserGuess() ?:-999
        val answerTextView  = findViewById<TextView>(R.id.answer)

        if(user_guess !in 1..25){

            answerTextView.text = "Not in range, Try again!"
            return
        }
        var message = ""
        if (user_guess == answer){
            answerTextView.text = "you got it right! number of attempts: $num_attempts"
            return
        }
        else{
            message = if (user_guess< answer) "Too low!" else "Too high!"
            answerTextView.text = message
            return
        }


    }
    fun getUserGuess(): Int? {
        val editTextGuess = findViewById<EditText>(R.id.editTextGuess)
        val userGuess = editTextGuess.text.toString()
        var guessAsInt = 0
        try {
            guessAsInt = userGuess.toInt()
        }
        catch (e:Exception){
            return null
        }
    return guessAsInt
    }
}