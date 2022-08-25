package com.vishishta.algovedixnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class QuestionsPane : AppCompatActivity() {

    lateinit var totalQuestions: TextView
    lateinit var questionTextView: TextView
    lateinit var ans_A:Button
    lateinit var ans_B:Button
    lateinit var ans_C:Button

    var vaat=0
    var pitt=0
    var cough=0
    var currentQuestionIndex=0
    var selectedAnswer: String=""

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions_pane)

        totalQuestions= findViewById(R.id.totalQuestions)
        questionTextView= findViewById(R.id.questionTextView)
        ans_A= findViewById(R.id.ans_A)
        ans_B= findViewById(R.id.ans_B)
        ans_C= findViewById(R.id.ans_C)


        ans_A.setOnClickListener {
            vaat += 1
            currentQuestionIndex+=1
            loadNewQuestion()
        }
        ans_B.setOnClickListener {
            pitt+=1
            currentQuestionIndex+=1
            loadNewQuestion()
        }
        ans_C.setOnClickListener {
            cough+=1
            currentQuestionIndex+=1
            loadNewQuestion()
        }


        loadNewQuestion()
    }

    fun loadNewQuestion(){
        if(currentQuestionIndex==15){
            finishQuiz()
            return
        }
        else {
            questionTextView.setText(QuestionAnswer.question[currentQuestionIndex])
            ans_A.setText(QuestionAnswer.choices[currentQuestionIndex][0])
            ans_B.setText(QuestionAnswer.choices[currentQuestionIndex][1])
            ans_C.setText(QuestionAnswer.choices[currentQuestionIndex][2])
        }
    }

    fun finishQuiz(){
        val intent = Intent (this, ResultsPage::class.java)
        intent.putExtra("vaat", vaat)
        intent.putExtra("pitt", pitt)
        intent.putExtra("cough", cough)
        startActivity(intent)
    }

}