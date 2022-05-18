package com.example.module7.model

class Math(override val position: Int, private val name: String, private var value: String): Expression {

    fun getExpression(): Pair<String, String> {
        return Pair(name,value)
    }
}