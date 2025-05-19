package com.example.historyquizapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.historyquizapp.databinding.ActivityScoreReviewBinding

class ScoreReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreReviewBinding
//score review and feedback screen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //to relay the score to the user
        val score = intent.getIntExtra("score", 0)
        val reviewArray = intent.getStringArrayExtra("review") ?: arrayOf()

        binding.scoreText.text = "You got $score out of 5 correct!"

        binding.feedbackText.text = if (score >= 3) {
            "Great job!"
        } else {
            "Keep practising!"
        }

        val reviewText = reviewArray.joinToString("\n")
        binding.reviewText.text = reviewText

        binding.exitButton.setOnClickListener {
            finishAffinity() // closes the app
        }
    }
}