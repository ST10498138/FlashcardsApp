package vcmsa.ci.flashcardapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {
    private val TAG = "ScoreActivity"

    private var score = 0
    private var totalQuestions = 0
    private var answeredQuestions: ArrayList<String>? = null
    private var allCorrectAnswers: ArrayList<String>? = null
    private var userAnswers: ArrayList<String>? = null // To store the user's answers

    private lateinit var scoreTextView: TextView
    private lateinit var feedbackTextView: TextView
    private lateinit var reviewButton: Button
    private lateinit var restartButton: Button
    private lateinit var quitButton: Button
    private lateinit var txtReviewQuestion: TextView
    private lateinit var txtReviewAnswer: TextView
    private lateinit var txtReviewFeedback: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        Log.d(TAG,"ScoreActivity is being initialized.onCreate called")


        // Retrieve data passed from QuestionActivity
        score = intent.getIntExtra("score", 0)
        totalQuestions = intent.getIntExtra("totalQuestions", 0)
        answeredQuestions = intent.getStringArrayListExtra("answeredQuestions")
        allCorrectAnswers = intent.getStringArrayListExtra("allCorrectAnswers")
        userAnswers = intent.getStringArrayListExtra("userAnswers")

        Log.i(TAG,"received data from intent : score= $score , totalQuestions =  $totalQuestions"+"answeredQuestions size = ${answeredQuestions?.size} , ${allCorrectAnswers?.size}"+"userAnswers size${userAnswers?.size}")
        // Retrieve views from the layout
        scoreTextView = findViewById(R.id.scoreTextView)
        feedbackTextView = findViewById(R.id.feedbackTextVIew)
        reviewButton = findViewById(R.id.reviewButton)
        restartButton = findViewById(R.id.restartButton)
        quitButton = findViewById(R.id.quitButton)
        txtReviewQuestion = findViewById(R.id.txtReviewQuestion)
        txtReviewAnswer = findViewById(R.id.txtReviewAnswer)
        txtReviewFeedback = findViewById(R.id.txtReviewFeedback)

        // Display the score
        scoreTextView.text = "Your score: $score / $totalQuestions"

        // Display personalized feedback based on the score
        val percentage = if (totalQuestions > 0) (score.toDouble() / totalQuestions * 100).toInt() else 0
        feedbackTextView.text = when (percentage) {
            in 80..100 -> "Excellent work!"
            in 60..79 -> "Well done!"
            in 40..59 -> "Not bad, keep practicing."
            else -> "You need to review some concepts."
        }
        Log.i(TAG , "score and feedback displayed : score = $score,feedback = '$feedbackTextView'.")

        // Initially hide the review details TextViews
        txtReviewQuestion.visibility = View.GONE
        txtReviewAnswer.visibility = View.GONE
        txtReviewFeedback.visibility = View.GONE

        //review btn
        reviewButton.setOnClickListener {
            Log.i(TAG , "review btn clicked. display the correct and incorrect answers.")
            if (!answeredQuestions.isNullOrEmpty() && !allCorrectAnswers.isNullOrEmpty() && !userAnswers.isNullOrEmpty()) {
                val correctList = mutableListOf<Int>()
                val incorrectList = mutableListOf<Int>()

                for (i in 0 until totalQuestions) {
                    if (userAnswers!![i] == allCorrectAnswers!![i]) {
                        correctList.add(i + 1) // Question numbers are 1-based
                    } else {
                        incorrectList.add(i + 1)
                    }
                }

                val correctFeedback = if (correctList.isNotEmpty()) {
                    "You answered questions ${correctList.joinToString(", ")} correctly."
                } else {
                    "You didn't answer any questions correctly."
                }

                val incorrectFeedback = if (incorrectList.isNotEmpty()) {
                    " Review questions ${incorrectList.joinToString(", ")}."
                } else {
                    ""
                }

                txtReviewFeedback.text = "$correctFeedback$incorrectFeedback"
                txtReviewFeedback.visibility = View.VISIBLE
                txtReviewQuestion.visibility = View.GONE // Hide individual question/answer
                txtReviewAnswer.visibility = View.GONE   // Hide individual question/answer

            } else {
                feedbackTextView.text = "No details to review."
                txtReviewFeedback.visibility = View.GONE
                txtReviewQuestion.visibility = View.GONE
                txtReviewAnswer.visibility = View.GONE
            }
        }

        // Restart btn
        restartButton.setOnClickListener {
            Log.i(TAG , "restart btn clicked. restarting the quiz.")
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
            finish() // Close the score activity
            Log.d(TAG,"restarted quiz.MainActivity started , scoreActivity finished")
        }

        //quit btn
        quitButton.setOnClickListener {
            Log.d(TAG , "Application exited via quit btn")
            finishAffinity() // Closes all activities
        }
    }
}