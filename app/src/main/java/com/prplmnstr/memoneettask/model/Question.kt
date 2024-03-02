package com.prplmnstr.memoneettask.model

data class Question(
    val id: Int,
    val question: String,
    val ans: Int,
    val options: List<String>
)