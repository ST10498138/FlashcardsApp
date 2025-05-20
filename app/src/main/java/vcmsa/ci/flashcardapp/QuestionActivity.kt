package vcmsa.ci.flashcardapp



import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuestionActivity : AppCompatActivity() {
    private val TAG = "QuestionActivity"

    private val questions = listOf(
        Question("True or False : Marie Curie was the first woman to receive a Nobel prize", "True"),
        Question("True or False : The berlin wall fell in 1995", "False"),
        Question("True or False : The invention of writing predates the invention of the wheel.", "True"),
        Question("Is this statement correct : South Africa was one of the founding members of the United Nation.", "False"),
        Question("Is this statement correct : The united states used nuclear weapons against Germany during World War II.", "False")
    )
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0
    private val userAnswers = mutableListOf<String>() // To store user's answers
    private val allCorrectAnswers = questions.map { it.correctAnswer } as ArrayList<String> // Store all correct answers
    private val answeredQuestionsList = questions.map { it.text } as ArrayList<String> // Store all questions

    private lateinit var editTextQuestionNum: TextView
    private lateinit var editTextQuestion: TextView
    private lateinit var editTextFeedback: TextView
    private lateinit var radioBtnTrue: RadioButton
    private lateinit var radioBtnFalse: RadioButton
    private lateinit var btnSubmit: Button
    private lateinit var btnNext: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question)
        Log.d(TAG , "questionActivity : onCreate called.")

        editTextQuestionNum = findViewById(R.id.txtNumbQuestion)
        editTextQuestion = findViewById(R.id.txtQuestion)
        editTextFeedback = findViewById(R.id.feedbackTextVIew)
        editTextFeedback.visibility = View.INVISIBLE //invisible
        radioBtnTrue = findViewById(R.id.radioBtnTrue)
        radioBtnFalse = findViewById(R.id.radioBtnFalse)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnNext = findViewById(R.id.btnNext)

        btnSubmit.setOnClickListener {
            checkAnswer()
        }


        btnNext.setOnClickListener {
            Log.d(TAG , "Next button clicked.calling loadNextQuestion().")
            loadNextQuestion()

        }

        //initialize the display of the first question
        displayQuestion()
        Log.i(TAG, "initial display of question number : ${currentQuestionIndex + 1}")

        // initially the next button is disabled
        btnNext.isEnabled = false
        Log.d(TAG,"Next button initially disabled")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun displayQuestion() {
        if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]
            editTextQuestionNum.text = "Question ${currentQuestionIndex + 1}/${questions.size}"
            editTextQuestion.text = question.text
            editTextFeedback.visibility = View.INVISIBLE // hide the feedback for the new question
            radioBtnTrue.isChecked = false // uncheck the radio btn
            radioBtnFalse.isChecked = false
            btnSubmit.isEnabled = true // activate the submit btn for the new question
            Log.i(TAG, "displaying question no. ${currentQuestionIndex + 1}:'${question.text}'")
            btnNext.isEnabled = false //uncheck the next btn until the submission
        } else {
            //navigate to the score screen when all the the question were answered
            Log.d(TAG, "All questions answered . Navigate to the score screen.")
            navigateToScoreScreen()
        }
    }

    private fun checkAnswer() {
        Log.d(TAG, "Entering check answer function.")
        if (currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]
            val userAnswer = when {
                radioBtnTrue.isChecked -> "True"
                radioBtnFalse.isChecked -> "False"
                else -> "" // No answer selected
            }

            userAnswers.add(userAnswer) // Store the user's answer

            editTextFeedback.visibility = View.VISIBLE // make feedback visible
            Log.d(TAG, "user selected:'$userAnswer'")
            if (userAnswer.isNotEmpty()) {
                Log.i(TAG, "user has selected an answer.proceeding to validate .")

                if (userAnswer == currentQuestion.correctAnswer) {
                    Log.i(TAG, "question ${currentQuestionIndex + 1} : correct answer selected.")
                    editTextFeedback.text = "Correct !\n${getAnecdote(currentQuestionIndex)}"
                    correctAnswersCount++
                } else {
                    Log.i(TAG,"Answer is incorrect for the question ${currentQuestionIndex + 1}. correct answer was: ${currentQuestion.correctAnswer}")
                    editTextFeedback.text = "Incorrect !\nThe correct answer was : ${currentQuestion.correctAnswer}\n${getAnecdote(currentQuestionIndex)}"
                }
                btnSubmit.isEnabled = false
                btnNext.isEnabled = true
                Log.d(TAG,"feedback displayed.submit button disabled , next btn enable")
            } else {
                Log.w(TAG,"user attempted to submit without selecting an answer.displaying warning")
                editTextFeedback.text = "Please select an answer."
            }
        }
    }

    private fun loadNextQuestion() {
        if (!btnSubmit.isEnabled) { // verify if there is an answer submitted
            currentQuestionIndex++
            displayQuestion()
        } else {
            editTextFeedback.visibility = View.VISIBLE // feedback must be visible
            Log.d(TAG,"no answer selected.submit btn remains enabled , next btn remains disabled")
            editTextFeedback.text = "Please submit your answer before moving on."
        }
    }

    private fun navigateToScoreScreen() {
        Log.d(TAG , "navigateToScoreScreen called . starting the navigation to scoreActivity ")
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("score", correctAnswersCount)
        intent.putExtra("totalQuestions", questions.size)
        intent.putStringArrayListExtra("answeredQuestions", answeredQuestionsList) // Pass the questions
        intent.putStringArrayListExtra("allCorrectAnswers", allCorrectAnswers) // Pass the correct answers
        intent.putStringArrayListExtra("userAnswers", userAnswers as ArrayList<String>) // Pass the user's answers
        Log.i(TAG,"starting scoreActivity with intent extras : score = $correctAnswersCount, totalQuestions = ${questions.size}")
        startActivity(intent)
        finish() // close activity question
        Log.d(TAG,"navigateToScoreScreen() finished . QuestionActivity finished. ")

    }

    private fun getAnecdote(questionIndex: Int): String {
        return when (questionIndex) {
            0 -> "Marie Curie was the first woman to receive a Nobel Prize (in physics in 1903) and the first person to receive two in different scientific fields (in chemistry in 1911) ."
            1 -> "The berlin wall fell on November 9,1989. It was the end of an era , East Germany opened its borders , people celebrated and began to destroy the berlin wall."
            2 -> "Writing was invented around 3200 BC , which is earlier than the widespread use of the wheel for transportation , which appeared around 3500 BC."
            3 -> "South Africa wasn't a founding member of the UN in 1945 , joining  a few weeks later."
            4 -> "The US used nuclear weapons against Japan (Hiroshima and Nagasaki, August 1945),while Germany had surrendered in May 1945"
            else -> "" // empty
        }
    }

    data class Question(val text: String, val correctAnswer: String)
}