package com.example.module7.model

class Conditions(override val position: Int, expression: String): Expression {
    private val condition = expression



    fun getData(): String {
        return condition
    }
}

