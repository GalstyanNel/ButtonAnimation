package com.example.correctandincorrectanweranimation

class QuestionModel(
    question: String = "",
    answer1: String = "",
    answer2: String = "",
    answer3: String = "",
    answer4: String = "",
    rightAnswer: String = ""

) {
    var question: String = ""
    var answer1: String = ""
    var answer2: String = ""
    var answer3: String = ""
    var answer4: String = ""
    var rightAnswer: String = ""

    init {
        this.question = question
        this.answer1 = answer1
        this.answer2 = answer2
        this.answer3 = answer3
        this.answer4 = answer4
        this.rightAnswer = rightAnswer

    }

}