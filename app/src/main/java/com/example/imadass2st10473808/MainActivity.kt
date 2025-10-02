package com.example.imadass2st10473808

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    //Array of the questions
    private val questions = arrayOf(
        "WW2 did not end in 1945",
        "Hitler invaded Poland First",
        "Japan did not attack Pearl Harbor",
        "America was on the AXIS side",
        "South Africa was Part of the allies in ww2"
    )
    //Answers to the questions
    private val answers = arrayOf(
        "False",
        "True",
        "False",
        "False",
        "True"
    )
    //Score and current question index
    private var Score = 0
    private var currentQuestionIndex = 0
    //Declaring UI elements
    private lateinit var Header1: TextView
    private lateinit var Start: Button
    private lateinit var Question: TextView
    private lateinit var True: Button
    private lateinit var False: Button
    private lateinit var Next: Button
    private lateinit var Restart: Button
    private lateinit var Quit: Button
    private lateinit var Review: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI elements
        Header1 = findViewById(R.id.Header1)
        Start = findViewById(R.id.Start)
        Question = findViewById(R.id.Question)
        True = findViewById(R.id.True)
        False = findViewById(R.id.False)
        Next = findViewById(R.id.Next)
        Restart = findViewById(R.id.Restart)
        Quit = findViewById(R.id.Quit)
        Review = findViewById(R.id.Review)

        // Hides game's buttons initially
        Question.visibility = View.GONE
        True.visibility = View.GONE
        False.visibility = View.GONE
        Next.visibility = View.GONE
        Restart.visibility = View.GONE
        Quit.visibility = View.GONE

        //launches the game
        Start.setOnClickListener {
            StartGame()
        }

        //Buttons for user input
        True.setOnClickListener {
            checkAnswer("True")
        }

        False.setOnClickListener {
            checkAnswer("False")
        }
        //When pressed moves onto the next question
        Next.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                showQuestion()
            } else {
                EndGame()
            }
        }
        //Restarts the game
        Restart.setOnClickListener {
            StartGame()
        }
        //closes the aplication
        Quit.setOnClickListener {
            finish()
        }
        //Shows the questions and answers
        Review.setOnClickListener {
            ShowReview()
        }
    }
    //Starts the game from the beginning
    private fun StartGame() {
        Score = 0
        currentQuestionIndex = 0
        //shows the game's UI
        Header1.visibility = View.GONE
        Start.visibility = View.GONE
        Question.visibility = View.VISIBLE
        True.visibility = View.VISIBLE
        False.visibility = View.VISIBLE
        Next.visibility = View.GONE
        Restart.visibility = View.GONE
        Quit.visibility = View.GONE
        Review.visibility = View.GONE
        //Shows the first question
        showQuestion()
    }
    //Displays the current questions
    private fun showQuestion() {
        Question.text = questions[currentQuestionIndex]
        True.isEnabled = true
        False.isEnabled = true
        Next.visibility = View.GONE
    }
    //Cheks if the users answers are correct
    private fun checkAnswer(userAnswer: String) {
        val correctAnswer = answers[currentQuestionIndex]

        if (userAnswer == correctAnswer) {
            Score++
            Question.text = "Correct"
        } else {
            Question.text = "Wrong"
        }
        True.isEnabled = false
        False.isEnabled = false
        Next.visibility = View.VISIBLE

    // Game ends. Display the final score.
    }
    private fun EndGame() {
        Question.text = if (Score > 3) {
            "Great job! Your score: $Score / ${questions.size}"
        } else {
            "Better luck next time! Your score: $Score / ${questions.size}"
        }

    //Hides gameplay buttons and shows the restart and quit buttons
        True.visibility = View.GONE
        False.visibility = View.GONE
        Next.visibility = View.GONE
        Restart.visibility = View.VISIBLE
        Quit.visibility = View.VISIBLE
        Review.visibility = View.VISIBLE
    }
        //Gives users the option to review the questions and answers
        private fun ShowReview() {
            val reviewText = StringBuilder()
            for (i in questions.indices) {
                reviewText.append("${i + 1}. ${questions[i]} - ${answers[i]}\n")
            }
            Question.text = reviewText.toString()

            True.visibility = View.GONE
            False.visibility = View.GONE
            Next.visibility = View.GONE
            Restart.visibility = View.VISIBLE
            Quit.visibility = View.VISIBLE
            Review.visibility = View.GONE
        }
    }







