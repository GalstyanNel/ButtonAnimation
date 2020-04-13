package com.example.correctandincorrectanweranimation

import android.animation.TimeAnimator
import android.content.Intent
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.time.Duration
import kotlin.math.min

class MainActivity : AppCompatActivity(), TimeAnimator.TimeListener {

    var listOfTests = arrayListOf<QuestionModel>()
    var currentQuestion = 0
    val LEVEL_INCREMENT = 1000
    val MAX_LEVEL = 10000
    var mAnimator: TimeAnimator? = null
    var mCurrentLevel = 0
    var mClipDrawable: ClipDrawable? = null
    lateinit var answersContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        answersContainer = findViewById(R.id.answersContainer)


        listOfTests.apply {
            add(0, QuestionModel("Question 1", "A", "B", "C", "D", "A"))
            add(1, QuestionModel("Question 2", "A", "B", "C", "D", "D"))
            add(2, QuestionModel("Question 3", "A", "B", "C", "D", "C"))
            add(
                3,
                QuestionModel("Question 4", "A", "B", "C", "D", "B")
            )
        }

        txtQuestion.text = listOfTests[currentQuestion].question
        btnAnswer1.text = listOfTests[currentQuestion].answer1
        btnAnswer2.text = listOfTests[currentQuestion].answer2
        btnAnswer3.text = listOfTests[currentQuestion].answer3
        btnAnswer4.text = listOfTests[currentQuestion].answer4

        for (i in 0..answersContainer.childCount) {
            answersContainer.getChildAt(i)?.setOnClickListener {
                checkAnswer(it as Button)

            }

        }

        buttonNext.setOnClickListener {
            if (currentQuestion < listOfTests.size - 1) {
//                mCurrentLevel = 0
                currentQuestion++
                txtQuestion.text = listOfTests[currentQuestion].question
                btnAnswer1.text = listOfTests[currentQuestion].answer1
                btnAnswer2.text = listOfTests[currentQuestion].answer2
                btnAnswer3.text = listOfTests[currentQuestion].answer3
                btnAnswer4.text = listOfTests[currentQuestion].answer4
                enableAnswersButton(true)

            } else {
                Toast.makeText(this, "finish", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun checkAnswer(selectedAnswer: Button) {
        enableAnswersButton(false)

        if (btnAnswer1.text.toString() == listOfTests[currentQuestion].rightAnswer) {
            val layerDrawable = selectedAnswer.background as LayerDrawable
            mClipDrawable =
                layerDrawable.findDrawableByLayerId(R.id.clip_drawable_true) as ClipDrawable

            mAnimator = TimeAnimator()
            mAnimator!!.setTimeListener(this)

            btnAnswer1.setOnClickListener {
                if (!mAnimator!!.isRunning) {
                    mCurrentLevel = 0;
                    mAnimator!!.start()
                }

            }
        } else {

//            val rightAnswer = answersContainer.findViewWithTag<Button>(listOfTests[currentQuestion].rightAnswer)
            val layerDrawable = selectedAnswer.background as LayerDrawable
            mClipDrawable =
                layerDrawable.findDrawableByLayerId(R.id.clip_drawable_false) as ClipDrawable

            mAnimator = TimeAnimator()
            mAnimator!!.setTimeListener(this)


            btnAnswer1.setOnClickListener {

                if (!mAnimator!!.isRunning) {
                    mCurrentLevel = 0;
                    mAnimator!!.start()
                }
            }


        }

        if (btnAnswer2.text.toString() == listOfTests[currentQuestion].rightAnswer) {
            val layerDrawable = selectedAnswer.background as LayerDrawable
            mClipDrawable =
                layerDrawable.findDrawableByLayerId(R.id.clip_drawable_true) as ClipDrawable

            mAnimator = TimeAnimator()
            mAnimator!!.setTimeListener(this)

            btnAnswer2.setOnClickListener {
                if (!mAnimator!!.isRunning) {
                    mCurrentLevel = 0;
                    mAnimator!!.start()
                }

            }
        } else {

//            val rightAnswer = answersContainer.findViewWithTag<Button>(listOfTests[currentQuestion].rightAnswer)
            val layerDrawable = selectedAnswer.background as LayerDrawable
            mClipDrawable =
                layerDrawable.findDrawableByLayerId(R.id.clip_drawable_false) as ClipDrawable

            mAnimator = TimeAnimator()
            mAnimator!!.setTimeListener(this)


            btnAnswer2.setOnClickListener {

                if (!mAnimator!!.isRunning) {
                    mCurrentLevel = 0;
                    mAnimator!!.start()
                }
            }


        }

        if (btnAnswer3.text.toString() == listOfTests[currentQuestion].rightAnswer) {
            val layerDrawable = selectedAnswer.background as LayerDrawable
            mClipDrawable =
                layerDrawable.findDrawableByLayerId(R.id.clip_drawable_true) as ClipDrawable

            mAnimator = TimeAnimator()
            mAnimator!!.setTimeListener(this)

            btnAnswer3.setOnClickListener {
                if (!mAnimator!!.isRunning) {
                    mCurrentLevel = 0;
                    mAnimator!!.start()
                }

            }
        } else {

//            val rightAnswer = answersContainer.findViewWithTag<Button>(listOfTests[currentQuestion].rightAnswer)
            val layerDrawable = selectedAnswer.background as LayerDrawable
            mClipDrawable =
                layerDrawable.findDrawableByLayerId(R.id.clip_drawable_false) as ClipDrawable

            mAnimator = TimeAnimator()
            mAnimator!!.setTimeListener(this)


            btnAnswer3.setOnClickListener {

                if (!mAnimator!!.isRunning) {
                    mCurrentLevel = 0;
                    mAnimator!!.start()
                }
            }


        }

    }

    override fun onTimeUpdate(animation: TimeAnimator?, totalTime: Long, deltaTime: Long) {
        mClipDrawable!!.level = mCurrentLevel
        if (mCurrentLevel >= MAX_LEVEL) {
            mAnimator!!.cancel()
        } else {
            mCurrentLevel = min(MAX_LEVEL, mCurrentLevel + LEVEL_INCREMENT)
        }

    }
    fun enableAnswersButton(enable: Boolean) {
        for (i in 0..4) {
            answersContainer.getChildAt(i)?.isEnabled = enable
//            if (enable) {
//                answersContainer.getChildAt(i)?.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#D4D4D8"))
//            }

        }
    }


}
