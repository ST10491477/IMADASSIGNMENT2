package com.example.historyquizapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.historyquizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
//Questions that the user will see and answer true or false
    private val questions = arrayOf(
        "Nelson Mandela was the president in 1994.",
        "World War II ended in 1945.",
        "The Great Wall of China was built in the 1900s.",
        "The Roman Empire was before the Middle Ages.",
        "The Berlin Wall fell in 1989."
    )

    private val answers = booleanArrayOf(true, true, false, true, true)
    private var currentQuestionIndex = 0
    private var score = 0
    private val reviewList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showQuestion()

        binding.trueButton.setOnClickListener { checkAnswer(true) }
        binding.falseButton.setOnClickListener { checkAnswer(false) }
//if and when statements
        binding.nextButton.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                showQuestion()
                binding.feedbackText.text = ""
            } else {
                val intent = Intent(this, ScoreReviewActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("review", reviewList.toTypedArray())
                startActivity(intent)
                finish()
            }
        }
    }

    private fun showQuestion() {
        binding.questionText.text = questions[currentQuestionIndex]
    }
//here you can see when the user gets a answer right or wrong
    private fun checkAnswer(userAnswer: Boolean) {
        val correct = answers[currentQuestionIndex]
        if (userAnswer == correct) {
            score++
            binding.feedbackText.text = "Correct!"
            reviewList.add("Q${currentQuestionIndex + 1}: ${questions[currentQuestionIndex]} - Correct")
        } else {
            binding.feedbackText.text = "Incorrect!"
            reviewList.add("Q${currentQuestionIndex + 1}: ${questions[currentQuestionIndex]} - Incorrect (Correct: ${if (correct) "True" else "False"})")
        }
    }
}