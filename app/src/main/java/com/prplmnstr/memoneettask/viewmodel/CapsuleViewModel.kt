package com.prplmnstr.memoneettask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prplmnstr.memoneettask.Utils.Constants.Companion.ANSWERED
import com.prplmnstr.memoneettask.Utils.Constants.Companion.CORRECT_ANSWER
import com.prplmnstr.memoneettask.Utils.Constants.Companion.SCORE
import com.prplmnstr.memoneettask.Utils.Constants.Companion.UNANSWERED
import com.prplmnstr.memoneettask.Utils.Constants.Companion.WRONG_ANSWER
import com.prplmnstr.memoneettask.Utils.ExampleQuestions
import com.prplmnstr.memoneettask.model.Question

class CapsuleViewModel : ViewModel() {
    var stopPosition: Int = 0

    var questionList = listOf<Question>()
    val ansList: MutableLiveData<List<Int>> = MutableLiveData()

    init {
        questionList = ExampleQuestions.loadQuestions()
        ansList.value = MutableList(questionList.size) { -1 }
    }


    fun formatSecondsToTime(seconds: Int): String {
        val minutes = (seconds % 3600) / 60
        val secs = seconds % 60
        return String.format("%02d:%02d", minutes, secs)
    }

    fun getResult(): Map<String, Int> {

        val answers = ansList.value!!
        var answered = 0
        var correctAnswer = 0
        for (i in 0..questionList.size - 1) {
            if (answers[i] != -1) {
                answered++
                if (questionList[i].ans == answers[i]) {
                    correctAnswer++
                }
            }
        }
        var unAnswered = questionList.size - answered
        var wrongAnswer = answered - correctAnswer
        var score = correctAnswer * 100 / questionList.size
        val map: HashMap<String, Int> = HashMap()
        map.put(ANSWERED, answered)
        map.put(UNANSWERED, unAnswered)
        map.put(CORRECT_ANSWER, correctAnswer)
        map.put(WRONG_ANSWER, wrongAnswer)
        map.put(SCORE, score)
        return map
    }
}