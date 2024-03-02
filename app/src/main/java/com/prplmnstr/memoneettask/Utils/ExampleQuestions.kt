package com.prplmnstr.memoneettask.Utils

import com.prplmnstr.memoneettask.model.Question

class ExampleQuestions {
    companion object {

        fun loadQuestions(): List<Question> {
            val questionList = mutableListOf<Question>()


            val question1 = Question(
                1,
                "___ and ___ are collectively known as twin characteristics of growth?",
                1,
                listOf(
                    "Increase in mass",
                    "Increase in number of individual cells",
                    "Increase in both mass and cells",
                    "Cell differentiation"
                )
            )
            val question2 = Question(
                2,
                "Which of the following do not reproduce?",
                2,
                listOf(
                    "Drone",
                    "Queen",
                    "Worker bee",
                    "All of these"
                )
            )

            val question3 = Question(
                3,
                "What is the blood color?",
                0,
                listOf(
                    "Red",
                    "Green",
                    "Black",
                    "Blue"
                )
            )
            val question4 = Question(
                4,
                "___ and ___ are collectively known as twin characteristics of growth",
                1,
                listOf(
                    "Increase in mass",
                    "Increase in number of individual cells",
                    "Increase in both mass and cells",
                    "cell differentiation"
                )
            )
            val question5 = Question(
                5,
                "___ and ___ are collectively known as twin characteristics of growth",
                2,
                listOf(
                    "Drone",
                    "Queen",
                    "Worker bee",
                    "All of these"
                )
            )
            val question6 = Question(
                6,
                "What is the blood color?",
                0,
                listOf(
                    "Red",
                    "Green",
                    "Black",
                    "Blue"
                )
            )
            val question7 = Question(
                7,
                "___ and ___ are collectively known as twin characteristics of growth",
                1,
                listOf(
                    "Increase in mass",
                    "Increase in number of individual cells",
                    "Increase in both mass and cells",
                    "cell differentiation"
                )
            )
            val question8 = Question(
                8,
                "___ and ___ are collectively known as twin characteristics of growth",
                2,
                listOf(
                    "Drone",
                    "Queen",
                    "Worker bee",
                    "All of these"
                )
            )
            val question9 = Question(
                9,
                "___ and ___ are collectively known as twin characteristics of growth",
                0,
                listOf(
                    "Red",
                    "Green",
                    "Black",
                    "Blue"
                )
            )
            val question10 = Question(
                10,
                "What is the blood color?",
                1,
                listOf(
                    "Increase in mass",
                    "Increase in number of individual cells",
                    "Increase in both mass and cells",
                    "Cell differentiation"
                )
            )


            questionList.add(question1)
            questionList.add(question2)
            questionList.add(question3)
            questionList.add(question4)
            questionList.add(question5)
            questionList.add(question6)
            questionList.add(question7)
            questionList.add(question8)
            questionList.add(question9)
            questionList.add(question10)

            return questionList
        }
    }
}