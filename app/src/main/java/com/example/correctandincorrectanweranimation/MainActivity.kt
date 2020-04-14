package com.example.correctandincorrectanweranimation

import android.animation.TimeAnimator
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.min

class MainActivity : AppCompatActivity(), TimeAnimator.TimeListener {

    var listOfTests = arrayListOf<QuestionModel>()
    var currentQuestion = 0
    val LEVEL_INCREMENT = 1000
    val MAX_LEVEL = 10000
    var mAnimator: TimeAnimator? = null
    var mCurrentLevel = 0
    var mClipDrawable: ClipDrawable? = null
    var mRightClipDrawable: ClipDrawable? = null
    var count = 0
    var isSelected: Boolean = false
    lateinit var answersContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        answersContainer = findViewById(R.id.answersContainer)


        listOfTests.apply {
            add(0, QuestionModel("Question 1", "A", "B", "C", "D", "A"))
            add(1, QuestionModel("Question 2", "A", "B", "C", "D", "D"))
            add(2, QuestionModel("Question 3", "A", "B", "C", "D", "C"))
            add(3, QuestionModel("Question 4", "A", "B", "C", "D", "B"))
        }

        txtQuestion.text = listOfTests[currentQuestion].question
        btnAnswer1.text = listOfTests[currentQuestion].answer1
        btnAnswer2.text = listOfTests[currentQuestion].answer2
        btnAnswer3.text = listOfTests[currentQuestion].answer3
        btnAnswer4.text = listOfTests[currentQuestion].answer4

        mAnimator = TimeAnimator()
        mAnimator!!.setTimeListener(this)

        for (i in 0..answersContainer.childCount) {
            answersContainer.getChildAt(i)?.setOnClickListener {
                it.isClickable = false
                checkAnswer(it as Button)
            }

        }

        buttonNext.setOnClickListener {
            if (currentQuestion < listOfTests.size - 1) {
                if (isSelected && !mAnimator!!.isRunning) {
                    currentQuestion++
                    txtQuestion.text = listOfTests[currentQuestion].question
                    btnAnswer1.text = listOfTests[currentQuestion].answer1
                    btnAnswer2.text = listOfTests[currentQuestion].answer2
                    btnAnswer3.text = listOfTests[currentQuestion].answer3
                    btnAnswer4.text = listOfTests[currentQuestion].answer4
                    enableButtons(true)
                    answersContainer.isClickable = true
                    count = 0
                } else {
                    Toast.makeText(this, "Select Answer", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Well Done!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun checkAnswer(selectedAnswer: Button) {
        enableButtons(false)
        mCurrentLevel = 0;

        isSelected = true
        if (selectedAnswer.text.toString() == listOfTests[currentQuestion].rightAnswer) {
            rightAnswerFunc(selectedAnswer)
        } else {
            findAndShowRightAnswer()

            val layerDrawable = selectedAnswer.background as LayerDrawable
            mClipDrawable =
                layerDrawable.findDrawableByLayerId(R.id.clip_drawable_false) as ClipDrawable
            if (!mAnimator!!.isRunning) {
                mAnimator!!.start()
            }

        }


    }

    override fun onTimeUpdate(animation: TimeAnimator?, totalTime: Long, deltaTime: Long) {
        Log.i("hhhhhhhhh", "onUpdate")
        if(mClipDrawable != null)
            mClipDrawable!!.level = mCurrentLevel
        if(mRightClipDrawable != null)
            mRightClipDrawable!!.level = mCurrentLevel
        if (mCurrentLevel >= MAX_LEVEL) {
            mAnimator!!.cancel()
        } else {
            mCurrentLevel = min(MAX_LEVEL, mCurrentLevel + LEVEL_INCREMENT)
        }

    }

    fun enableButtons(enable: Boolean) {
        for (i in 0..4) {
            answersContainer.getChildAt(i)?.isClickable = enable
            if (enable) {
                if(mClipDrawable != null)
                    mClipDrawable!!.level = 0
                if(mRightClipDrawable != null)
                    mRightClipDrawable!!.level = 0
            }
        }
    }

    fun findAndShowRightAnswer() {
        Log.e("childCount", answersContainer.childCount.toString())
        for (i in 0..4) {
            when (listOfTests[currentQuestion].rightAnswer) {
                btnAnswer1.text.toString() -> {
                    rightAnswerFunc(btnAnswer1)
                }
                btnAnswer2.text.toString() -> {
                    rightAnswerFunc(btnAnswer2)
                }
                btnAnswer3.text.toString() -> {
                    rightAnswerFunc(btnAnswer3)
                }
                btnAnswer4.text.toString() -> {
                    rightAnswerFunc(btnAnswer4)
                }
            }

        }
    }

    fun rightAnswerFunc(selectedAnswer: Button) {
        val layerDrawable = selectedAnswer.background as LayerDrawable
        mRightClipDrawable =
            layerDrawable.findDrawableByLayerId(R.id.clip_drawable_true) as ClipDrawable
        if (!mAnimator!!.isRunning) {
            mAnimator!!.start()
        }
    }
}



